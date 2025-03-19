package com.example.cricket.service;

import com.example.cricket.Beans.Match;
import com.example.cricket.repository.MatchRepo;
import com.example.cricket.utility.TeamChecker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
@Slf4j
public class TournamentRunnerService {
    private final MatchRunnerService matchRunnerService;
    private final MatchRepo matchRepo;
    private final TaskExecutor taskExecutor;
    private final TeamChecker teamChecker;
    private final ThreadPoolTaskScheduler taskScheduler;
    private final MatchMakerService matchMakerService;
    private final PointsTableService pointsTableService;

    private final AtomicBoolean tournamentRunning = new AtomicBoolean(false);
    private boolean flag = false;

    public void startTournament(String tournamentId) {
        if (!flag) {
            taskScheduler.scheduleAtFixedRate(
                    () -> matchChecker(tournamentId),
                    3000
            );
        }
    }

    public void matchChecker(String tournamentId) {
        synchronized (this) {
            try {
                List<Match> pendingMatches = matchRepo.findPendingByTournamentId(tournamentId);

                if (pendingMatches.isEmpty()) {
                    if (flag) {
                        stopTournament(tournamentId);
                        return;
                    }
                    flag = true;
                    matchMakerService.makeMatches(pointsTableService.finalTeams(tournamentId), tournamentId);
                }

                for (Match match : pendingMatches) {
                    boolean check = teamChecker.checkTeams(match.getTeamA(), match.getTeamB());
                    if (check) {
                        teamChecker.addTeams(match);
                        taskExecutor.execute(() -> matchRunnerService.runMatch(match));
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException( e.getMessage(), e);
            }
        }
    }

    public void stopTournament(String tournamentId) {
        if (flag) {
            System.out.println("tournament ended!!");
            taskScheduler.shutdown();
        }
    }
}