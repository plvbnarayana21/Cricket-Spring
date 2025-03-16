package com.example.cricket.service;

import com.example.cricket.Beans.*;
import com.example.cricket.dto.ScoreCardDTO;
import com.example.cricket.repository.ScoreCardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScoreCardService {

    @Autowired
    private ScoreCardRepo scoreCardRepo;

    @Transactional
    public synchronized void update(List<Player> batting, List<Player> bowling, int runs, int balls, int wickets, Innings innings, Match match, int iNo) {
        // Create and save the ScoreCard entity
        ScoreCard sc = ScoreCard.builder()
                .bowling(bowling)
                .batting(batting)
                .totalRuns(runs)
                .wickets(wickets)
                .ballsplayed(balls)
                .inning(innings.getId())
                .match(match.getId())
                .iNo(iNo)
                .build();

        scoreCardRepo.save(sc);
    }

    @Transactional(readOnly = true)
    public ScoreCardDTO getthruId(String id) {
        // Fetch the ScoreCard entity
        ScoreCard sc = scoreCardRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Scorecard not found"));

        // Convert to DTO
        return new ScoreCardDTO(
                sc.getId(),
                sc.getBatting(),
                sc.getBowling(),
                sc.getTotalRuns(),
                sc.getWickets(),
                sc.getBallsplayed(),
                sc.getInning(),
                sc.getMatch(),
                sc.getINo()
        );
    }
}