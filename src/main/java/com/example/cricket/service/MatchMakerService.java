package com.example.cricket.service;

import com.example.cricket.Beans.Match;
//import com.example.cricket.Beans.PointsTable;
import com.example.cricket.Beans.Team;
//import com.example.cricket.Beans.TeamPoints;
import com.example.cricket.repository.MatchRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchMakerService {
    private final TeamService teamService;
    private final MatchRepo matchRepo;
//    private final PointsTableService pointsTableService;

    public ResponseEntity<String> makeMatches(List<String> teamNames, String tournamentId) {
        Map<String, Team> teams = teamNames.stream()
                .collect(Collectors.toMap(name -> name, teamService::getByname));
        int cnt = 0;
        System.out.println("inside match maker");
        int n = teamNames.size();
        if (n == 2) {
            createMatch(teams.get(teamNames.get(0)), teams.get(teamNames.get(1)), tournamentId, "final");
            return ResponseEntity.ok("matches made successfully!!!");

        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    createMatch(teams.get(teamNames.get(i)), teams.get(teamNames.get(j)), tournamentId, "league");
                }
            }
        }
//        pointsTableService.createPoints(teamNames,tournamentId);

        return ResponseEntity.ok("matches made successfully!!!");
    }


    private void createMatch(Team teamA, Team teamB, String tournamentId, String type) {
        Match match = Match.builder()
                .teamA(teamA)
                .teamB(teamB)
                .matchType(type)
                .tournamentId(tournamentId)
                .location(teamA.getName())
                .status("PENDING")
                .highestScore(0)
                .tId(0).build();
        matchRepo.save(match);
    }

    public void updateStatus(Match match) {
        match.setStatus("IN PROGRESS");
        matchRepo.save(match);
    }
}