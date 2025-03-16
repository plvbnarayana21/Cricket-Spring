package com.example.cricket.controller;

import com.example.cricket.dto.MatchResponseDTO;
import com.example.cricket.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping("/start")
    public ResponseEntity<String> startMatch(@RequestParam String team1Name,
                                             @RequestParam String team2Name,
                                             @RequestParam String team3Name,
                                             @RequestParam String team4Name) {
        return matchService.startTwoMatches(team1Name, team2Name, team3Name, team4Name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchResponseDTO> getById(@PathVariable String id) {
        MatchResponseDTO matchResponse = matchService.getById(id);
        return ResponseEntity.ok(matchResponse);
    }
}