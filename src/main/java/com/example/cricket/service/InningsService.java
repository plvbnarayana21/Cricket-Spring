package com.example.cricket.service;

import com.example.cricket.Beans.Innings;
import com.example.cricket.Beans.Match;
import com.example.cricket.Beans.Team;
import com.example.cricket.businessLogic.Inning;
import com.example.cricket.dto.InningsDTO;
import com.example.cricket.repository.InningsRepo;
import com.example.cricket.repository.InningsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InningsService {
    private final InningsRepository inningsRepository;
    private final ApplicationContext applicationContext;
    private final InningsRepo inningsRepo;

    public Innings startInnings(Match match, Team battingTeam, Team bowlingTeam, int inningsNo, int target) {
        Innings innings = Innings.builder()
                .battingTeam(battingTeam)
                .bowlingTeam(bowlingTeam)
                .iNo(inningsNo)
                .totalRuns(0)
                .wicketsLost(0)
                .ballsPlayed(0)
                .oversToPlay(10)
                .match(match)
                .build();

        innings = inningsRepository.save(innings);

        Inning inningLogic = applicationContext.getBean(Inning.class);
        inningLogic.init(battingTeam.getPlayers(), bowlingTeam.getPlayers(), innings, match, inningsNo);
        int runs = inningLogic.startInnings(10, target);

        innings.setTotalRuns(runs);
        innings.setWicketsLost(inningLogic.getWicketsLost());
        innings.setBallsPlayed(inningLogic.getBallsBowled());

        return inningsRepository.save(innings);
    }

    public InningsDTO getByMatchIdAndINo(String matchId, int iNo) {
        Innings innings = inningsRepo.findByMatchIdAndINo(matchId, iNo)
                .orElseThrow(() -> new RuntimeException("Innings not found for Match ID: " + matchId + " and Innings Number: " + iNo));

    return new InningsDTO(innings.getId(),innings.getBattingTeam().getName(),innings.getBowlingTeam().getName(),
            innings.getTotalRuns(),innings.getWicketsLost(),innings.getBallsPlayed(),innings.getOversToPlay(),
            innings.getMatch().getId(),innings.getINo()
    );
    }


}