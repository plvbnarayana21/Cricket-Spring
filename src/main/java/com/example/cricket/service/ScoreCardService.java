package com.example.cricket.service;

import com.example.cricket.Beans.*;
import com.example.cricket.dto.ScoreCardDTO;
import com.example.cricket.repository.ScoreCardRepo;
import com.example.cricket.repository.ScoreCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreCardService {

//    @Autowired
//    private ScoreCardRepository scoreCardRepo;

    @Autowired
    private ScoreCardRepo scoreCardRepo;


    public void update(List<Player> batting, List<Player> bowling, int runs, int balls, int wickets, Innings innings, Match match,int iNo) {
        ScoreCard sc = ScoreCard.builder().bowling(bowling).batting(batting).totalRuns(runs).wickets(wickets).
                ballsplayed(balls).inning(innings.getId()).match(match.getId()).iNo(iNo).build();

        scoreCardRepo.save(sc);
    }

    public ScoreCardDTO getthruId(String id) {

        ScoreCard sc = scoreCardRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Scorecard not found"));

        // should be in DTO class or separate helper class
        return new ScoreCardDTO(
             sc.getId(),sc.getBatting(),sc.getBowling(),sc.getTotalRuns(),sc.getWickets(),sc.getBallsplayed(),
                sc.getInning(),sc.getMatch(),sc.getINo()
        );
    }
}

