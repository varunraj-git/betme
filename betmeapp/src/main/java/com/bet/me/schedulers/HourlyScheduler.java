package com.bet.me.schedulers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.bet.me.dao.GameRepository;
import com.bet.me.dao.OddsRepository;
import com.bet.me.model.OddsData;
import com.bet.me.model.Sport;
import com.bet.me.service.SportsService;

@Configuration
@Transactional
public class HourlyScheduler {
	private final Logger log = LoggerFactory.getLogger(HourlyScheduler.class);
	private final SportsService sportsService;
	private final OddsRepository oddsRepo;
	private final GameRepository gameRepo;

	public HourlyScheduler(final SportsService sportsService, final OddsRepository oddsRepo,
			final GameRepository gameRepo) {
		this.sportsService = sportsService;
		this.oddsRepo = oddsRepo;
		this.gameRepo = gameRepo;
	}

	@Scheduled(fixedDelay = 60 * 60 * 1000, initialDelay=60000)
	public void runHourlyScheduler() {
		//gets the dat from the DB
		final List<Sport> sports = this.gameRepo.findAll();
		if (!CollectionUtils.isEmpty(sports)) {
			sports.forEach(sprt -> {
				final List<OddsData> oddsData = this.sportsService.getOddsData(sprt.getKey(), "uk", "h2h");
				//saves or updates the db
				this.oddsRepo.saveAll(oddsData);
			
			});

		}
	}

}
