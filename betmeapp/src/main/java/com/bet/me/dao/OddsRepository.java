package com.bet.me.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bet.me.model.OddsData;

@Repository

public interface OddsRepository extends MongoRepository<OddsData, String> {
	

}
