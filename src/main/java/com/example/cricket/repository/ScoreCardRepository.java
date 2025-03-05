package com.example.cricket.repository;

import com.example.cricket.Beans.ScoreCard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScoreCardRepository extends MongoRepository<ScoreCard,String> {

}
