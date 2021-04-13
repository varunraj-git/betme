package com.bet.me.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.bet.me.service.SportsService;

@Configuration
public class GetUpcomingFixturesCommandLineRunner implements CommandLineRunner {
	private final Logger logger = LoggerFactory.getLogger(GetUpcomingFixturesCommandLineRunner.class);
	private final SportsService sportsService;

	public GetUpcomingFixturesCommandLineRunner(final SportsService sportsService) {
		this.sportsService = sportsService;
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("APP START - caching upcoming matches");
		this.sportsService.getUpcomingMatches();
	}

}