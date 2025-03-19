package com.example.cricket.controller;

import com.example.cricket.Beans.Match;
import com.example.cricket.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournament")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @PostMapping("/start")
    public ResponseEntity<String> startTournament(
            @RequestParam String team1Name,
            @RequestParam String team2Name,
            @RequestParam String team3Name,
            @RequestParam String team4Name) {
        List<String> teams = List.of(team1Name, team2Name, team3Name, team4Name);
        return tournamentService.startTournament(teams);
    }


}