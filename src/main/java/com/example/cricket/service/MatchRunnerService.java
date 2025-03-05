package com.example.cricket.service;

import com.example.cricket.Beans.Innings;
import com.example.cricket.Beans.Match;
import com.example.cricket.Beans.Team;
import com.example.cricket.dto.TossResult;
import com.example.cricket.repository.MatchRepo;
import com.example.cricket.repository.MatchRepository;
import lombok.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
@Data
public class MatchRunnerService {
    private final TossService tossService;
    //    private final MatchRepository matchRepository;
    private final MatchRepo matchRepo;
    private final InningsService inningsService;
    private final PlayerService playerService;
    private Team battingTeam;
    private Team bowlingTeam;

    public void runMatch(Team teamA, Team teamB) {
        Match match = createMatch(teamA, teamB);
        Innings firstInnings = inningsService.startInnings(match, this.battingTeam, this.bowlingTeam, 1, Integer.MAX_VALUE);
        int target = firstInnings.getTotalRuns() + 1;
        Innings secondInnings = inningsService.startInnings(match, this.bowlingTeam, this.battingTeam, 2, target);
        endMatch(match, firstInnings, secondInnings);
    }

    private Match createMatch(Team teamA, Team teamB) {
        TossResult tossResult = tossService.conductToss(teamA, teamB);
        Team tossWinner = tossResult.getTossWinner();
        String choice = tossResult.getChoice();

        this.battingTeam = choice.equals("bat") ? tossWinner : (tossWinner == teamA ? teamB : teamA);
        this.bowlingTeam = battingTeam == teamA ? teamB : teamA;

        playerService.memReset(battingTeam.getPlayers());
        playerService.memReset(bowlingTeam.getPlayers());

        Match match = Match.builder()
                .teamA(teamA)
                .teamB(teamB)
                .tossWinner(tossWinner.getName())
                .tossChoice(choice)
                .build();

        return matchRepo.save(match);
    }

    private void endMatch(Match match, Innings firstInnings, Innings secondInnings) {
        match.setMatchWinner(determineWinner(firstInnings, secondInnings));
        match.setFirstInnings(firstInnings);
        match.setSecondInnings(secondInnings);
        matchRepo.save(match);
    }

    private String determineWinner(Innings firstInnings, Innings secondInnings) {
        if (firstInnings.getTotalRuns() > secondInnings.getTotalRuns()) {
            return firstInnings.getBattingTeam().getName();
        } else if (firstInnings.getTotalRuns() < secondInnings.getTotalRuns()) {
            return secondInnings.getBattingTeam().getName();
        } else {
            return "Draw";
        }
    }
}
