//////package com.example.cricket.service;
//////
//////import com.example.cricket.Beans.*;
//////import com.example.cricket.dto.TossResult;
//////import com.example.cricket.repository.MatchRepo;
//////import lombok.*;
//////import org.springframework.stereotype.*;
//////import java.util.concurrent.ExecutorService;
//////import java.util.concurrent.Executors;
//////
//////@Service
//////@RequiredArgsConstructor
//////public class MatchRunnerService {
//////    private final TossService tossService;
//////    private final MatchRepo matchRepo;
//////    private final InningsService inningsService;
//////    private final PlayerService playerService;
//////
//////    private final ExecutorService matchExecutor = Executors.newFixedThreadPool(2);
//////
//////    private final ThreadLocal<Team> threadLocalBattingTeam = new ThreadLocal<>();
//////    private final ThreadLocal<Team> threadLocalBowlingTeam = new ThreadLocal<>();
//////    private final ThreadLocal<Match> threadLocalMatch = new ThreadLocal<>();
//////
//////    public void runMatch(Team teamA, Team teamB) {
//////        matchExecutor.submit(() -> {
//////            try {
//////                initializeThreadLocalState(teamA, teamB);
//////
//////                MatchContext context = createMatchContext();
//////                Innings firstInnings = inningsService.startInnings(
//////                        context.getMatch(),
//////                        context.getBattingTeam(),
//////                        context.getBowlingTeam(),
//////                        1,
//////                        Integer.MAX_VALUE
//////                );
//////                int target = firstInnings.getTotalRuns() + 1;
//////                Innings secondInnings = inningsService.startInnings(
//////                        context.getMatch(),
//////                        context.getBowlingTeam(),
//////                        context.getBattingTeam(),
//////                        2,
//////                        target
//////                );
//////                endMatch(context.getMatch(), firstInnings, secondInnings);
//////            } catch (Exception e) {
//////                System.err.println("Error running match: " + e.getMessage());
//////            } finally {
//////                threadLocalBattingTeam.remove();
//////                threadLocalBowlingTeam.remove();
//////                threadLocalMatch.remove();
//////            }
//////        });
//////    }
//////
//////    private void initializeThreadLocalState(Team teamA, Team teamB) {
//////        TossResult tossResult = tossService.conductToss(teamA, teamB);
//////        Team tossWinner = tossResult.getTossWinner();
//////        String choice = tossResult.getChoice();
//////
//////        threadLocalBattingTeam.set(choice.equals("bat") ? tossWinner : (tossWinner == teamA ? teamB : teamA));
//////        threadLocalBowlingTeam.set(threadLocalBattingTeam.get() == teamA ? teamB : teamA);
//////
//////        playerService.memReset(threadLocalBattingTeam.get().getPlayers());
//////        playerService.memReset(threadLocalBowlingTeam.get().getPlayers());
//////
//////        // Create and save the match
//////        Match match = Match.builder()
//////                .teamA(teamA)
//////                .teamB(teamB)
//////                .tossWinner(tossWinner.getName())
//////                .tossChoice(choice)
//////                .build();
//////
//////        matchRepo.save(match);
//////        threadLocalMatch.set(match);
//////    }
//////
//////    private MatchContext createMatchContext() {
//////        return new MatchContext(
//////                threadLocalMatch.get(),
//////                threadLocalBattingTeam.get(),
//////                threadLocalBowlingTeam.get()
//////        );
//////    }
//////
//////    private void endMatch(Match match, Innings firstInnings, Innings secondInnings) {
//////        match.setMatchWinner(determineWinner(firstInnings, secondInnings));
//////        match.setFirstInnings(firstInnings);
//////        match.setSecondInnings(secondInnings);
//////        matchRepo.save(match);
//////    }
//////
//////    private String determineWinner(Innings firstInnings, Innings secondInnings) {
//////        if (firstInnings.getTotalRuns() > secondInnings.getTotalRuns()) {
//////            return firstInnings.getBattingTeam().getName();
//////        } else if (firstInnings.getTotalRuns() < secondInnings.getTotalRuns()) {
//////            return secondInnings.getBattingTeam().getName();
//////        } else {
//////            return "Draw";
//////        }
//////    }
//////}
////
////package com.example.cricket.service;
////
////import com.example.cricket.Beans.*;
////import com.example.cricket.dto.TossResult;
////import com.example.cricket.repository.MatchRepo;
////import lombok.RequiredArgsConstructor;
////import org.springframework.stereotype.Service;
////
////import java.util.concurrent.ExecutorService;
////import java.util.concurrent.Executors;
////
////@Service
////@RequiredArgsConstructor
////public class MatchRunnerService {
////    private final TossService tossService;
////    private final MatchRepo matchRepo;
////    private final InningsService inningsService;
////    private final PlayerService playerService;
////
////    // Thread pool for concurrent matches
////    private final ExecutorService matchExecutor = Executors.newFixedThreadPool(2);
////
////    public void runMatch(Team teamA, Team teamB) {
////        matchExecutor.submit(() -> {
////            try {
////                MatchContext context = createMatchContext(teamA, teamB);
////                Innings firstInnings = inningsService.startInnings(
////                        context.getMatch(),
////                        context.getBattingTeam(),
////                        context.getBowlingTeam(),
////                        1,
////                        Integer.MAX_VALUE
////                );
////                int target = firstInnings.getTotalRuns() + 1;
////                Innings secondInnings = inningsService.startInnings(
////                        context.getMatch(),
////                        context.getBowlingTeam(),
////                        context.getBattingTeam(),
////                        2,
////                        target
////                );
////                endMatch(context.getMatch(), firstInnings, secondInnings);
////            } catch (Exception e) {
////                System.err.println("Error running match: " + e.getMessage());
////            }
////        });
////    }
////
////    private MatchContext createMatchContext(Team teamA, Team teamB) {
////        TossResult tossResult = tossService.conductToss(teamA, teamB);
////        Team tossWinner = tossResult.getTossWinner();
////        String choice = tossResult.getChoice();
////
////        Team battingTeam = choice.equals("bat") ? tossWinner : (tossWinner == teamA ? teamB : teamA);
////        Team bowlingTeam = (battingTeam == teamA) ? teamB : teamA;
////
////        playerService.memReset(battingTeam.getPlayers());
////        playerService.memReset(bowlingTeam.getPlayers());
////
////        Match match = Match.builder()
////                .teamA(teamA)
////                .teamB(teamB)
////                .tossWinner(tossWinner.getName())
////                .tossChoice(choice)
////                .build();
////
////        matchRepo.save(match);
////
////        return new MatchContext(match, battingTeam, bowlingTeam);
////    }
////
////    private void endMatch(Match match, Innings firstInnings, Innings secondInnings) {
////        match.setMatchWinner(determineWinner(firstInnings, secondInnings));
////        match.setFirstInnings(firstInnings);
////        match.setSecondInnings(secondInnings);
////        matchRepo.save(match);
////    }
////
////    private String determineWinner(Innings firstInnings, Innings secondInnings) {
////        if (firstInnings.getTotalRuns() > secondInnings.getTotalRuns()) {
////            return firstInnings.getBattingTeam().getName();
////        } else if (firstInnings.getTotalRuns() < secondInnings.getTotalRuns()) {
////            return secondInnings.getBattingTeam().getName();
////        } else {
////            return "Draw";
////        }
////    }
////}
package com.example.cricket.service;

import com.example.cricket.Beans.*;
import com.example.cricket.dto.TossResult;
import com.example.cricket.repository.MatchRepo;
import jakarta.annotation.PostConstruct;
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

    // Thread pool for concurrent matches
    private final ExecutorService matchExecutor = Executors.newFixedThreadPool(2);

    public void runMatch(Team teamA, Team teamB) {
        matchExecutor.submit(() -> {
            try {
                MatchContext context = createMatchContext(teamA, teamB);
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

    private MatchContext createMatchContext(Team teamA, Team teamB) {
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
                .tossWinner(tossWinner.getName())
                .tossChoice(choice)
                .build();

        matchRepo.save(match);

        return new MatchContext(match, battingTeam, bowlingTeam);
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
//public class MatchRunnerService {
//    private final TossService tossService;
//    private final MatchRepo matchRepo;
//    private final InningsService inningsService;
//    private final PlayerService playerService;
//
//    // Thread pool for concurrent matches
//    private final ExecutorService matchExecutor = Executors.newFixedThreadPool(4); // Adjust pool size
//
//    // BlockingQueue to hold pending matches
//    private final BlockingQueue<Match> matchQueue = new LinkedBlockingQueue<>();
//
//    @PostConstruct
//    public void startConsumerThreads() {
//        for (int i = 0; i < 4; i++) { // Start 4 consumer threads
//            matchExecutor.submit(() -> {
//                while (true) {
//                    try {
//                        Match match = matchQueue.take(); // Block until a match is available
//                        processMatch(match);
//                    } catch (InterruptedException e) {
//                        Thread.currentThread().interrupt();
//                        break;
//                    }
//                }
//            });
//        }
//    }
//
//    public void addMatchToQueue(Match match) {
//        matchQueue.add(match); // Add match to the queue
//    }
//
//    private void processMatch(Match match) {
//        try {
//            match.setStatus("inProgress");
//            matchRepo.save(match);
//
//            MatchContext context = createMatchContext(match.getTeamA(), match.getTeamB());
//            Innings firstInnings = inningsService.startInnings(
//                    context.getMatch(),
//                    context.getBattingTeam(),
//                    context.getBowlingTeam(),
//                    1,
//                    Integer.MAX_VALUE
//            );
//            int target = firstInnings.getTotalRuns() + 1;
//            Innings secondInnings = inningsService.startInnings(
//                    context.getMatch(),
//                    context.getBowlingTeam(),
//                    context.getBattingTeam(),
//                    2,
//                    target
//            );
//            endMatch(context.getMatch(), firstInnings, secondInnings);
//        } catch (Exception e) {
//            match.setStatus("pending"); // Retry later
//            matchRepo.save(match);
//            System.err.println("Error running match: " + e.getMessage());
//        }
//    }
//
//    private MatchContext createMatchContext(Team teamA, Team teamB) {
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
//                .tossWinner(tossWinner.getName())
//                .tossChoice(choice)
//                .build();
//
//        matchRepo.save(match);
//
//        return new MatchContext(match, battingTeam, bowlingTeam);
//    }
//
//    private void endMatch(Match match, Innings firstInnings, Innings secondInnings) {
//        match.setMatchWinner(determineWinner(firstInnings, secondInnings));
//        match.setFirstInnings(firstInnings);
//        match.setSecondInnings(secondInnings);
//        match.setStatus("completed");
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