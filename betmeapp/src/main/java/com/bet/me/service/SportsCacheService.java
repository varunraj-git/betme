package com.bet.me.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.bet.me.dao.OddsRepository;
import com.bet.me.model.OddsData;
import com.bet.me.model.Site;

@Service
public class SportsCacheService {
	private final CacheManager cacheManager;
	private final OddsRepository oddsRepo;

	public SportsCacheService(final CacheManager cacheManager, final OddsRepository oddsRepo) {
		this.cacheManager = cacheManager;
		this.oddsRepo = oddsRepo;
	}

	@CachePut(value = "odds-data", key = "#oddsData.id")
	public OddsData cacheOddsData(final OddsData oddsData) {
		return oddsData;
	}

	/*
	 * this function retrieves cached data
	 */
	public List<OddsData> getCachedOddsData() {
		final Map<Object, Object> cache = this.getCacheMap();
		List<OddsData> oddsList = new ArrayList<>();

		cache.forEach((k, v) -> oddsList.add((OddsData) v));
		return oddsList;
	}

	/*
	 * this function retrieves cached data given the sports key
	 */
	public List<OddsData> getCachedSportsOddsData(final String sportsKey) {
		final Map<Object, Object> cache = this.getCacheMap();
		List<OddsData> oddsList = new ArrayList<>();

		cache.forEach((k, v) -> {
			final OddsData oddsd = (OddsData) v;
			if (oddsd.getSport_key().equalsIgnoreCase(sportsKey)) {
				oddsList.add(oddsd);
			}
		});
		return oddsList;
	}

	/*
	 * this function is called by the scheduler to update real time cache
	 * if not present in new data, invalidate from cache using odds key
	 * else
	 * update existing cache using odds key
	 */
	public void updateCacheRealtime(final List<OddsData> oddsDataList) {
		final Map<Object, Object> cache = this.getCacheMap();

		cache.forEach((k, v) -> {
			final OddsData oddC = (OddsData) v;
			final String key = (String) k;

			Optional<OddsData> oddA = oddsDataList.stream().filter(dt -> dt.getId().equals(key)).findFirst();
			if (oddA.isPresent()) {
				if (!this.isSitesSame(oddA.get().getSites(), oddC.getSites())) {
					this.getCache().put(oddA.get().getId(), oddA);
					this.oddsRepo.save(oddA.get());
				}

			} else {
				this.getCache().evict(k);
			}
		});

	}

	/*
	 * common method to get cache data from cache manager
	 */
	private Map<Object, Object> getCacheMap() {
		return (Map<Object, Object>) this.cacheManager.getCache("odds-data").getNativeCache();
	}

	private Cache getCache() {
		return this.cacheManager.getCache("odds-data");
	}

	/*
	 * given list of two sites, check each element present in the other, if not return immediatly.
	 * O(n^2) operation
	 */
	private boolean isSitesSame(final List<Site> sitea, final List<Site> siteb) {
		if (sitea.size() != siteb.size()) {
			return false;
		}
		
		for(int i=0; i<sitea.size(); i++) {
			Site siteFirst = sitea.get(i);
			if(!siteb.contains(siteFirst)) {
				return false;
			}
		}
		return true;
	}

}
