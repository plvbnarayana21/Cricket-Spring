
package com.example.cricket.service;

import com.example.cricket.Beans.*;
import com.example.cricket.dto.TossResult;
import com.example.cricket.repository.MatchRepo;
import com.example.cricket.utility.Threads;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

@Service
@RequiredArgsConstructor
public class MatchRunnerService {
    private final TossService tossService;
    private final MatchRepo matchRepo;
    private final InningsService inningsService;
    private final PlayerService playerService;

    private final ExecutorService matchExecutor = Executors.newFixedThreadPool(3);

    public void runMatch(Team teamA, Team teamB) {
        matchExecutor.submit(() -> {
            try {
                long tId= Thread.currentThread().getId();
                MatchContext context = createMatchContext(teamA, teamB,tId);
                Innings firstInnings = inningsService.startInnings(
                        context.getMatch(),
                        context.getBattingTeam(),
                        context.getBowlingTeam(),
                        1,
                        Integer.MAX_VALUE
                );
                int target = firstInnings.getTotalRuns() + 1;
                Innings secondInnings = inningsService.startInnings(
                        context.getMatch(),
                        context.getBowlingTeam(),
                        context.getBattingTeam(),
                        2,
                        target
                );
                endMatch(context.getMatch(), firstInnings, secondInnings);
            } catch (Exception e) {
                System.err.println("Error running match: " + e.getMessage());
            }
        });
    }

    private MatchContext createMatchContext(Team teamA, Team teamB,long tId) {
        TossResult tossResult = tossService.conductToss(teamA, teamB);
        Team tossWinner = tossResult.getTossWinner();
        String choice = tossResult.getChoice();

        Team battingTeam = choice.equals("bat") ? tossWinner : (tossWinner == teamA ? teamB : teamA);
        Team bowlingTeam = (battingTeam == teamA) ? teamB : teamA;

        playerService.memReset(battingTeam.getPlayers());
        playerService.memReset(bowlingTeam.getPlayers());

        Match match = Match.builder()
                .teamA(teamA)
                .teamB(teamB)
                .tId(tId)
                .tossWinner(tossWinner.getName())
                .tossChoice(choice)
                .build();

        matchRepo.save(match);

        return new MatchContext(match, battingTeam, bowlingTeam,tId);
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