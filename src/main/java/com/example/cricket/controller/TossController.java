package com.example.cricket.controller;

import com.example.cricket.dto.TossResponseDTO;
import com.example.cricket.Beans.Team;
import com.example.cricket.dto.TossResult;
import com.example.cricket.service.TeamService;
import com.example.cricket.service.TossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/toss")
public class TossController {

    @Autowired
    private TossService tossService;

    @Autowired
    private TeamService teamService;

    @GetMapping("/byid")
    public TossResult tossBetweenTeams(@RequestParam String team1Id, @RequestParam String team2Id) {
        Team team1 = teamService.getTeamById(team1Id);
        Team team2 = teamService.getTeamById(team2Id);
        return tossService.conductToss(team1, team2);
    }

    @PostMapping("/byname")
    @ResponseStatus(HttpStatus.OK)
    public TossResponseDTO conductToss(
            @RequestParam String teamAName,
            @RequestParam String teamBName,
            @RequestParam String tossCaller,
            @RequestParam String tossCall,
            @RequestParam String choice
    ) {
        return tossService.conductToss(teamAName, teamBName, tossCaller, tossCall, choice);
    }
}
