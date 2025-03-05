package com.example.cricket.repository;

import com.example.cricket.Beans.Match;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MatchRepository extends MongoRepository<Match,String>{
}