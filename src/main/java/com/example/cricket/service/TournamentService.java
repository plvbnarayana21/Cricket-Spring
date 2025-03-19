
package com.example.cricket.service;

import com.example.cricket.Beans.Tournament;
import com.example.cricket.repository.TournamentRepo;
import com.example.cricket.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final MatchMakerService matchMakerService;
    private final TournamentRepo tournamentRepo;
    private final TournamentRunnerService tournamentRunnerService;
    private final PointsTableService pointsTableService;


    public ResponseEntity<String> startTournament(List<String> teams) {
        Tournament tournament = createTournament(teams);
        pointsTableService.createPointsTable(teams,tournament.getId());
        matchMakerService.makeMatches(teams, tournament.getId());
        tournamentRunnerService.startTournament(tournament.getId());
        return ResponseEntity.ok("Tournament started successfully!");
    }

    private Tournament createTournament(List<String> teams) {
        Tournament tournament = Tournament.builder()
                .name("CT")
                .teams(teams)
                .build();
        return tournamentRepo.save(tournament);
    }
    public void deleteTournaments(){
        tournamentRepo.deleteAll();
    }

}