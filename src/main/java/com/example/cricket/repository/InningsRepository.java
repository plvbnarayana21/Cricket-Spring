package com.example.cricket.repository;

import com.example.cricket.Beans.Innings;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface InningsRepository extends MongoRepository<Innings,String> {
    List<Innings> findByMatchId(String matchId);
}
