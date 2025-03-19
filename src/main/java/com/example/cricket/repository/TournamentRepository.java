package com.example.cricket.repository;

import com.example.cricket.Beans.Match;
import com.example.cricket.Beans.Tournament;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentRepository extends MongoRepository<Tournament, String> {
    Tournament findMatchesById(String id);
}
