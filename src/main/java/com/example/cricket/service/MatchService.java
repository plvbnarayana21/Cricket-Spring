package com.example.cricket.service;

import com.example.cricket.Beans.*;
import com.example.cricket.dto.MatchResponseDTO;
import com.example.cricket.repository.MatchRepo;
import com.example.cricket.utility.TeamChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchRunnerService matchRunnerService;
    private final TeamService teamService;
    private final PlayerService playerService;
    private final MatchRepo matchRepo;
    private final TaskExecutor taskExecutor;
    private final TeamChecker teamChecker;

    public ResponseEntity<String> startTwoMatches(String team1Name, String team2Name, String team3Name, String team4Name) {
        Team teamA = teamService.getByname(team1Name);
        Team teamB = teamService.getByname(team2Name);
        Team teamC = teamService.getByname(team3Name);
        Team teamD = teamService.getByname(team4Name);

        playerService.updatePlayersMatchCount(teamA);
        playerService.updatePlayersMatchCount(teamB);
        playerService.updatePlayersMatchCount(teamC);
        playerService.updatePlayersMatchCount(teamD);


//        taskExecutor.execute(() -> matchRunnerService.runMatch(teamA, teamB));
//        taskExecutor.execute(()->matchRunnerService.runMatch(teamA,teamC));
//        taskExecutor.execute(() -> matchRunnerService.runMatch(teamC, teamD));

        return ResponseEntity.ok("Two matches started successfully and running in the background.");
    }

    public MatchResponseDTO getById(String id) {
        Match match = matchRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Match does not exist with id: " + id));

        return new MatchResponseDTO(
                match.getId(),
                match.getFirstInnings().getBattingTeam().getName(),
                match.getSecondInnings().getBattingTeam().getName(),
                match.getFirstInnings() != null ? match.getFirstInnings().getTotalRuns() : 0,
                match.getSecondInnings() != null ? match.getSecondInnings().getTotalRuns() : 0,
                match.getFirstInnings() != null ? match.getFirstInnings().getWicketsLost() : 0,
                match.getSecondInnings() != null ? match.getSecondInnings().getWicketsLost() : 0,
                match.getFirstInnings() != null ? match.getFirstInnings().getBallsPlayed() : 0,
                match.getSecondInnings() != null ? match.getSecondInnings().getBallsPlayed() : 0,
                match.getMatchWinner()
        );
    }
}
