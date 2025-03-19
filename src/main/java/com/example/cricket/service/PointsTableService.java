package com.example.cricket.service;

import com.example.cricket.Beans.Match;
import com.example.cricket.Beans.PointsTable;
import com.example.cricket.Beans.TeamPoints;
import com.example.cricket.repository.PointsTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PointsTableService {

    private final PointsTableRepository pointsTableRepository;

    public synchronized void updateWinnerPoints(Match match) {
        String winner = match.getMatchWinner();
        String loser = winner.equalsIgnoreCase(match.getTeamA().getName())
                ? match.getTeamB().getName()
                : match.getTeamA().getName();

        PointsTable pointsTable = pointsTableRepository.findByTournamentId(match.getTournamentId());
        if (pointsTable == null) {
            throw new RuntimeException("Points table not found for tournament ID: " + match.getTournamentId());
        }

        for (TeamPoints teamPoints : pointsTable.getTeamPoints()) {
            if (teamPoints.getTeamName().equalsIgnoreCase(winner)) {
                teamPoints.setPoints(teamPoints.getPoints() + 2);
                if (match.getHighestScore() > teamPoints.getHighestScore()) {
                    teamPoints.setHighestScore(match.getHighestScore());
                }
                teamPoints.setMatchesPlayed(teamPoints.getMatchesPlayed() + 1);
                teamPoints.setMatchesWon(teamPoints.getMatchesWon() + 1);
            } else if (teamPoints.getTeamName().equalsIgnoreCase(loser)) {
                teamPoints.setMatchesPlayed(teamPoints.getMatchesPlayed() + 1);
                teamPoints.setMatchesLost(teamPoints.getMatchesLost() + 1);
            }
        }

        pointsTableRepository.save(pointsTable);
    }


    public void deleteTable() {
        pointsTableRepository.deleteAll();
    }

    public void createPointsTable(List<String> teams, String tournamentId) {
        PointsTable pointsTable = PointsTable.builder()
                .tournamentId(tournamentId)
                .build();

        for (String team : teams) {
            TeamPoints teamPoints = TeamPoints.builder()
                    .teamName(team)
                    .matchesPlayed(0)
                    .matchesWon(0)
                    .highestScore(0)
                    .matchesLost(0)
                    .Points(0).build();
            pointsTable.getTeamPoints().add(teamPoints);
        }

        pointsTableRepository.save(pointsTable);
    }

    public List<String> finalTeams(String tournamentId) {
        PointsTable pointsTable = pointsTableRepository.findByTournamentId(tournamentId);
        if (pointsTable == null) {
            throw new RuntimeException("Points table not found for tournament ID: " + tournamentId);
        }
        List<TeamPoints> sortedTeams = pointsTable.getTeamPoints().stream()
                .sorted((tp1, tp2) -> {
                    int pointsComparison = Integer.compare(tp2.getPoints(), tp1.getPoints());
                    if (pointsComparison != 0) {
                        return pointsComparison;
                    }
                    return Integer.compare(tp2.getHighestScore(), tp1.getHighestScore());
                })
                .collect(Collectors.toList());

        for (TeamPoints tp : sortedTeams) {
            System.out.println(tp.getTeamName() + " | Points: " + tp.getPoints() + " | Highest Score: " + tp.getHighestScore());
        }

        List<String> topTeams = new ArrayList<>();
        for (int i = 0; i < 2 && i < sortedTeams.size(); i++) {
            topTeams.add(sortedTeams.get(i).getTeamName());
        }
        System.out.println(topTeams);
        return topTeams;
    }
//    public PointsTable getPointsTable(String tournamentId) {
//        return pointsTableRepository.findByTournamentId(tournamentId);
//    }
//
//    public TeamPoints getTeamPoints(String teamName, String tournamentId) {
//        PointsTable pointsTable = pointsTableRepository.findByTournamentId(tournamentId);
//        if (pointsTable == null) {
//            throw new RuntimeException("Points table not found for tournament ID: " + tournamentId);
//        }
//
//        return pointsTable.getTeamPoints().stream()
//                .filter(tp -> tp.getTeamName().equalsIgnoreCase(teamName))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Team not found in points table: " + teamName));
//    }

}