//
//package com.example.cricket.service;
//
//import com.example.cricket.Beans.*;
//import com.example.cricket.Exception.TeamAlreadyInMatchException;
//import com.example.cricket.dto.TossResult;
//import com.example.cricket.repository.MatchRepo;
//import com.example.cricket.utility.TeamChecker;
//import jakarta.annotation.PreDestroy;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.concurrent.*;
//
//@Service
//@RequiredArgsConstructor
//public class MatchRunnerService {
//    private static final Logger logger = LoggerFactory.getLogger(MatchRunnerService.class);
//
//    private final TossService tossService;
//    private final MatchRepo matchRepo;
//    private final InningsService inningsService;
//    private final PlayerService playerService;
//    private final TeamChecker teamChecker;
//
//    private final ExecutorService matchExecutor = Executors.newFixedThreadPool(2);
//
//    public void runMatch(Team teamA, Team teamB) {
//        matchExecutor.submit(() -> {
//            try {
//                long tId = Thread.currentThread().getId();
//                MatchContext context = createMatchContext(teamA, teamB, tId);
//
//                if (teamChecker.checker(context.getMatch())) {
//                    throw new TeamAlreadyInMatchException("Either " + teamA.getName() + " or " + teamB.getName() + " is already in a match.");
//                }
//                context.getMatch().setStartTime(LocalDateTime.now());
//
//                teamChecker.addToMap(context.getMatch());
//
//                // Start the match
//                Innings firstInnings = inningsService.startInnings(
//                        context.getMatch(),
//                        context.getBattingTeam(),
//                        context.getBowlingTeam(),
//                        1,
//                        Integer.MAX_VALUE
//                );
//                int target = firstInnings.getTotalRuns() + 1;
//                Innings secondInnings = inningsService.startInnings(
//                        context.getMatch(),
//                        context.getBowlingTeam(),
//                        context.getBattingTeam(),
//                        2,
//                        target
//                );
//                context.getMatch().setEndTime(LocalDateTime.now());
//
//                endMatch(context.getMatch(), firstInnings, secondInnings);
//
//                teamChecker.removeFromMap(context.getMatch());
//            } catch (TeamAlreadyInMatchException e) {
//                logger.error("Error: {}", e.getMessage());
//            } catch (Exception e) {
//                logger.error("Error running match: {}", e.getMessage(), e);
//            }
//        });
//    }
//
//    @PreDestroy
//    public void cleanup() {
//        matchExecutor.shutdown();
//        try {
//            if (!matchExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
//                matchExecutor.shutdownNow();
//            }
//        } catch (InterruptedException e) {
//            matchExecutor.shutdownNow();
//            Thread.currentThread().interrupt();
//        }
//    }
//
//    private MatchContext createMatchContext(Team teamA, Team teamB, long tId) {
//        TossResult tossResult = tossService.conductToss(teamA, teamB);
//        Team tossWinner = tossResult.getTossWinner();
//        String choice = tossResult.getChoice();
//
//        Team battingTeam = choice.equals("bat") ? tossWinner : (tossWinner == teamA ? teamB : teamA);
//        Team bowlingTeam = (battingTeam == teamA) ? teamB : teamA;
//
//        playerService.memReset(battingTeam.getPlayers());
//        playerService.memReset(bowlingTeam.getPlayers());
//
//        Match match = Match.builder()
//                .teamA(teamA)
//                .teamB(teamB)
//                .tId(tId)
//                .tossWinner(tossWinner.getName())
//                .tossChoice(choice)
//                .build();
//
//        matchRepo.save(match);
//
//        return new MatchContext(match, battingTeam, bowlingTeam, tId);
//    }
//
//    private void endMatch(Match match, Innings firstInnings, Innings secondInnings) {
//        match.setMatchWinner(determineWinner(firstInnings, secondInnings));
//        match.setFirstInnings(firstInnings);
//        match.setSecondInnings(secondInnings);
//        matchRepo.save(match);
//    }
//
//    private String determineWinner(Innings firstInnings, Innings secondInnings) {
//        if (firstInnings.getTotalRuns() > secondInnings.getTotalRuns()) {
//            return firstInnings.getBattingTeam().getName();
//        } else if (firstInnings.getTotalRuns() < secondInnings.getTotalRuns()) {
//            return secondInnings.getBattingTeam().getName();
//        } else {
//            return "Draw";
//        }
//    }
//}

package com.example.cricket.service;

import com.example.cricket.Beans.*;
import com.example.cricket.Exception.TeamAlreadyInMatchException;
import com.example.cricket.dto.TossResult;
import com.example.cricket.repository.MatchRepo;
import com.example.cricket.utility.TeamChecker;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
public class MatchRunnerService {
    private static final Logger logger = LoggerFactory.getLogger(MatchRunnerService.class);

    private final TossService tossService;
    private final MatchRepo matchRepo;
    private final InningsService inningsService;
    private final PlayerService playerService;
    private final TeamChecker teamChecker;

    private final ExecutorService matchExecutor = Executors.newFixedThreadPool(2);

    public void runMatch(Team teamA, Team teamB) {
        matchExecutor.submit(() -> {
            try {
                long tId = Thread.currentThread().getId();
                MatchContext context = createMatchContext(teamA, teamB, tId);

                context.getMatch().setStartTime(LocalDateTime.now());
                context.getMatch().setStatus("Inprogress");
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

                context.getMatch().setEndTime(LocalDateTime.now());
                endMatch(context.getMatch(), firstInnings, secondInnings);

                teamChecker.removeTeams(context.getMatch().getTeamA(),context.getMatch().getTeamB());
            } catch (TeamAlreadyInMatchException e) {
                logger.error("Error: {}", e.getMessage());
            } catch (Exception e) {
                logger.error("Error running match: {}", e.getMessage(), e);
            }
        });
    }

    @PreDestroy
    public void cleanup() {
        matchExecutor.shutdown();
        try {
            if (!matchExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                matchExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            matchExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private MatchContext createMatchContext(Team teamA, Team teamB, long tId) {
        Match match = Match.builder()
                .teamA(teamA)
                .teamB(teamB)
                .tId(tId)
                .build();

        teamChecker.checkAndAddTeams(teamA,teamB);
        TossResult tossResult = tossService.conductToss(teamA, teamB);
        Team tossWinner = tossResult.getTossWinner();
        String choice = tossResult.getChoice();

        Team battingTeam = choice.equals("bat") ? tossWinner : (tossWinner == teamA ? teamB : teamA);
        Team bowlingTeam = (battingTeam == teamA) ? teamB : teamA;

        match.setTossWinner(tossWinner.getName());
        match.setTossChoice(choice);

        playerService.memReset(battingTeam.getPlayers());
        playerService.memReset(bowlingTeam.getPlayers());


        matchRepo.save(match);

        return new MatchContext(match, battingTeam, bowlingTeam, tId);
    }

    private void endMatch(Match match, Innings firstInnings, Innings secondInnings) {
        match.setMatchWinner(determineWinner(firstInnings, secondInnings));
        match.setFirstInnings(firstInnings);
        match.setSecondInnings(secondInnings);
        match.setStatus("COMPLETED");
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