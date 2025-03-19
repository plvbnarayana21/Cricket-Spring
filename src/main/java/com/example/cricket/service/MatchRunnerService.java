package com.example.cricket.service;

import com.example.cricket.Beans.*;
import com.example.cricket.Exception.TeamAlreadyInMatchException;
import com.example.cricket.dto.MatchDTO;
import com.example.cricket.dto.TossResult;
import com.example.cricket.repository.MatchRepo;
import com.example.cricket.utility.TeamChecker;
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
    private final PointsTableService pointsTableService;
    private final TossService tossService;
    private final MatchRepo matchRepo;
    private final InningsService inningsService;
    private final PlayerService playerService;
    private final TeamChecker teamChecker;

    private final ExecutorService matchExecutor = Executors.newFixedThreadPool(2);
//private final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
//    private final ThreadPoolExecutor matchExecutor = new ThreadPoolExecutor(
//            2, 4, 1, TimeUnit.MINUTES, queue);

    public void runMatch(Match match) {
        matchExecutor.submit(() -> {
            try {
                System.out.println("entered match");
                long tId = Thread.currentThread().getId();
                MatchDTO context = createMatchDTO(match, tId);

                context.getMatch().setStartTime(LocalDateTime.now());
                context.getMatch().setStatus("IN PROGRESS");

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

                teamChecker.removeTeams(context.getMatch().getTeamA(), context.getMatch().getTeamB());
                Thread.sleep(2000);
            } catch (TeamAlreadyInMatchException e) {
                logger.error("Error: {}", e.getMessage());
            } catch (Exception e) {
                logger.error("Error running match: {}", e.getMessage(), e);
            }
        });
    }


    private MatchDTO createMatchDTO(Match match, long tId) {
        Team teamA = match.getTeamA();
        Team teamB = match.getTeamB();

        TossResult tossResult = tossService.conductToss(teamA, teamB);
        Team tossWinner = tossResult.getTossWinner();
        String choice = tossResult.getChoice();

        Team battingTeam = choice.equals("bat") ? tossWinner : (tossWinner == teamA ? teamB : teamA);
        Team bowlingTeam = (battingTeam == teamA) ? teamB : teamA;

        match.setTossWinner(tossWinner.getName());
        match.setTossChoice(choice);
        match.setTId(tId);

        playerService.updatePlayersMatchCount(teamA);
        playerService.updatePlayersMatchCount(teamB);
        playerService.memReset(battingTeam.getPlayers());
        playerService.memReset(bowlingTeam.getPlayers());

        matchRepo.save(match);

        return new MatchDTO(match, battingTeam, bowlingTeam, tId);
    }

    private void endMatch(Match match, Innings firstInnings, Innings secondInnings) {
        match.setMatchWinner(determineWinner(firstInnings, secondInnings,match));
        match.setFirstInnings(firstInnings);
        match.setSecondInnings(secondInnings);
        match.setStatus("COMPLETED");
        teamChecker.removeTeams(match.getTeamA(), match.getTeamB());
        pointsTableService.updateWinnerPoints(match);
        matchRepo.save(match);
    }

    private String determineWinner(Innings firstInnings, Innings secondInnings,Match match) {
        if (firstInnings.getTotalRuns() > secondInnings.getTotalRuns()) {
            match.setHighestScore(firstInnings.getTotalRuns());
            return firstInnings.getBattingTeam().getName();
        } else if (firstInnings.getTotalRuns() < secondInnings.getTotalRuns()) {
            match.setHighestScore(secondInnings.getTotalRuns());
            return secondInnings.getBattingTeam().getName();
        } else {
            return "Draw";
        }
    }
}