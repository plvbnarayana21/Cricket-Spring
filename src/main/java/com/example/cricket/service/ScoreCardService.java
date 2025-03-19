package com.example.cricket.service;

import com.example.cricket.Beans.*;
import com.example.cricket.dto.ScoreCardDTO;
import com.example.cricket.repository.ScoreCardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ScoreCardService {

    @Autowired
    private ScoreCardRepo scoreCardRepo;

//    private final AtomicInteger totalRuns = new AtomicInteger(0);
    public synchronized void update(List<Player> batting, List<Player> bowling, int runs, int balls, int wickets, Innings innings, Match match, int iNo) {
//        System.out.println("Adding Scorecards between"+match.getTeamA()+" and "+match.getTeamB());
//        int truns=runs.intValue(),twickets=wickets.intValue(),tballs=balls.intValue();
        ScoreCard sc = ScoreCard.builder()
                .bowling(bowling)
                .batting(batting)
                .totalRuns(runs)
                .wickets(balls)
                .ballsplayed(wickets)
                .inning(innings.getId())
                .match(match.getId())
                .iNo(iNo)
                .build();

        scoreCardRepo.save(sc);
    }

    public ScoreCardDTO getthruId(String id) {
        ScoreCard sc = scoreCardRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Scorecard not found"));

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