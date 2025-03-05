package com.example.cricket.repository;

import com.example.cricket.Beans.Innings;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.*;

public interface InningsRepository extends MongoRepository<Innings,String> {
    List<Innings> findByMatchId(String matchId);

}
