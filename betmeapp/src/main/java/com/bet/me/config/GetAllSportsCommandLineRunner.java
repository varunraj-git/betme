package com.bet.me.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import com.bet.me.dao.GameRepository;
import com.bet.me.dao.OddsRepository;
import com.bet.me.model.OddsData;
import com.bet.me.model.Sport;
import com.bet.me.service.SportsService;

@Configuration
public class GetAllSportsCommandLineRunner implements CommandLineRunner {
	Logger logger = LoggerFactory.getLogger(GetAllSportsCommandLineRunner.class);

	private final SportsService sportsService;
	private final GameRepository gameRepo;
	private final OddsRepository oddsRepo;

	public GetAllSportsCommandLineRunner(final SportsService sportsService,
			final GameRepository gameRepo,
			final OddsRepository oddsRepo) {
		this.sportsService = sportsService;
		this.gameRepo = gameRepo;
		this.oddsRepo = oddsRepo; 
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("APP START - Populating the sports database");
		final List<Sport> sports = sportsService.getAllSports();
		if(CollectionUtils.isEmpty(sports)) {
		logger.warn("Api returned empty collection");
			return;
		}
		
		this.gameRepo.saveAll(sports);
			 sports.forEach(sprt -> { 
				  final List<OddsData> oddsData =this.sportsService.getOddsData(sprt.getKey(), "uk", "h2h");
				  this.oddsRepo.saveAll(oddsData);
			  });
		
		
	}

}
