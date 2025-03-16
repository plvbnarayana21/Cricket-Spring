package com.example.cricket.service;

import com.example.cricket.Beans.*;
import com.example.cricket.dto.MatchResponseDTO;
import com.example.cricket.repository.MatchRepo;
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

    public ResponseEntity<String> startTwoMatches(String team1Name, String team2Name, String team3Name, String team4Name) {
        // Fetch teams
        Team teamA = teamService.getByname(team1Name);
        Team teamB = teamService.getByname(team2Name);
        Team teamC = teamService.getByname(team3Name);
        Team teamD = teamService.getByname(team4Name);

        // Update player match counts
        playerService.updatePlayersMatchCount(teamA);
        playerService.updatePlayersMatchCount(teamB);
        playerService.updatePlayersMatchCount(teamC);
        playerService.updatePlayersMatchCount(teamD);

        // Start match 1: teamA vs teamB
        taskExecutor.execute(() -> matchRunnerService.runMatch(teamA, teamB));

        // Start match 2: teamC vs teamD
        taskExecutor.execute(() -> matchRunnerService.runMatch(teamC, teamD));

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
//package com.example.cricket.service;
//
//import com.example.cricket.Beans.Match;
//import com.example.cricket.Beans.Team;
//import com.example.cricket.repository.MatchRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class MatchService {
//
//    @Autowired
//    private MatchRepo matchRepo;
//
//    @Autowired
//    private MatchRunnerService matchRunnerService;
//
//    // Create a new match and add it to the BlockingQueue
//    public Match createMatch(Team teamA, Team teamB, String tournamentId) {
//        Match match = Match.builder()
//                .teamA(teamA)
//                .teamB(teamB)
//                .tournamentId(tournamentId)
//                .status("pending") // Set initial status to pending
//                .build();
//
//        matchRepo.save(match); // Save the match to the database
//        matchRunnerService.addMatchToQueue(match); // Add match to the BlockingQueue
//        return match;
//    }
//
//    // Get match by ID
//    public Match getMatchById(String id) {
//        return matchRepo.findById(id)
//                .orElseThrow(() -> new RuntimeException("Match not found with id: " + id));
//    }
//
//    // Get all pending matches for a tournament
//    public List<Match> getPendingMatches(String tournamentId) {
//        return matchRepo.findPendingByTournamentId(tournamentId);
//    }
//
//    // Update match status
//    public void updateMatchStatus(String matchId, String status) {
//        Match match = matchRepo.findById(matchId)
//                .orElseThrow(() -> new RuntimeException("Match not found with id: " + matchId));
//        match.setStatus(status);
//        matchRepo.save(match);
//    }
//}