package com.example.cricket.repository;

import com.example.cricket.Beans.Match;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends MongoRepository<Match,String>{
    List<Match> findByTournamentId(String tournamentId);

}