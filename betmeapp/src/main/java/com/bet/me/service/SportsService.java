package com.bet.me.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.bet.me.client.RetrieveApiDataFeignClient;
import com.bet.me.dao.GameRepository;
import com.bet.me.dao.OddsRepository;
import com.bet.me.exception.BetMeApiException;
import com.bet.me.model.ApiResponse;
import com.bet.me.model.OddsData;
import com.bet.me.model.Sport;

@Service
public class SportsService {
	private Logger logger = LoggerFactory.getLogger(SportsService.class);
	public static final String KEY = "uk-h2h";

	private final RetrieveApiDataFeignClient sportsClient;
	private final SportsCacheService sportsCacheService;
	private final GameRepository gameRepo;
	private final OddsRepository oddsRepo;

	public SportsService(final RetrieveApiDataFeignClient sportsClient,
			final CacheManager cacheManager,
			final SportsCacheService sportsCacheService,
			 final GameRepository gameRepo,
			 final OddsRepository oddsRepo) {
		this.sportsClient = sportsClient;
		this.sportsCacheService = sportsCacheService;
		this.gameRepo = gameRepo;
		this.oddsRepo = oddsRepo;
	}

	/*
	 * This function will return all sports from api
	 */
	public List<Sport> getAllSports() {
		logger.info("calling the api to get sports data");
		final ApiResponse<Sport> apiResponse = this.sportsClient.getSportsResponse();
		if (!apiResponse.success) {
			throw new BetMeApiException("failure from api");
		}
		return apiResponse.getData();
	}
	
	public List<Sport> getAllSportsFromDb() {
		logger.info("calling the database to get sports data");
		return this.gameRepo.findAll();
	}

	/*
	 * this function will return all odds given sport, region and mkt from api
	 */
	public List<OddsData> getOddsData(final String sport, final String region, final String mkt) {
		final ApiResponse<OddsData> apiResponse = sportsClient.getOddsData(sport, region, mkt);
		if (!apiResponse.success) {
			throw new BetMeApiException("failure from api");
		}
		return apiResponse.getData();
	}
	
	/*This function will get odds data from the DB
	 * */
	public List<OddsData> getOddsDataFromDb() {
		logger.info("retrieving odds data from db");
		return this.oddsRepo.findAll();
	}
	
	

	public void getUpcomingMatches() {
		final List<OddsData> oddsDataList = this.getOddsData("upcoming", "uk", "h2h");
		oddsDataList.forEach(this.sportsCacheService::cacheOddsData);
	}
	
	public List<OddsData> getCachedOddsData() {
		return this.sportsCacheService.getCachedOddsData();
	}
	
	public List<OddsData> getCachedSportOddsData(final String sportsKey) {
		return this.sportsCacheService.getCachedSportsOddsData(sportsKey);	
	}
	
	
	public void updateCacheRealtime() {
		final List<OddsData> oddsDataList = this.getOddsData("upcoming", "uk", "h2h");
		this.sportsCacheService.updateCacheRealtime(oddsDataList);
		
	}

	

}
