package com.bet.me.schedulers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import com.bet.me.service.SportsService;

@Configuration
@Transactional
public class RealTimeScheduler  {
	
	private final Logger log = LoggerFactory.getLogger(RealTimeScheduler.class);
	private final SportsService sportsService;
	public RealTimeScheduler(final SportsService sportsService) {
		this.sportsService = sportsService;
	}
	
	
	@Scheduled(fixedRateString  = "${betmeapp.realtime.delayms}")
	public void runRealTimeCache() {
		log.info("Running realtime scheduler");
		this.sportsService.updateCacheRealtime();
			
	}
	
	
	
}
