package com.bet.me.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bet.me.model.Sport;
@Repository
public interface GameRepository extends MongoRepository<Sport, String> {

}
