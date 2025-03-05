package com.example.cricket.controller;

import com.example.cricket.dto.TeamResponseDTO;
import com.example.cricket.Beans.Team;
import com.example.cricket.dto.TossResult;
import com.example.cricket.service.TeamService;
import com.example.cricket.service.TossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @Autowired
    private TossService tossService;

    @GetMapping("/{teamId}")
    public TeamResponseDTO getTeamById(@PathVariable String teamId) {
        return teamService.TeamById(teamId);
    }

    @GetMapping("/name/{name}")
    public TeamResponseDTO getTeamByName(@PathVariable String name) {
        return teamService.getTeamByName(name);
    }

    @GetMapping("/allteams")
    public List<TeamResponseDTO> getallteams(){
        return teamService.getAllTeams();
    }

    @GetMapping("/toss")
    public TossResult tossBetweenTeams(@RequestParam String team1Id, @RequestParam String team2Id) {
        //all the logic should be in service layer
        Team team1 = teamService.getTeamById(team1Id);
        Team team2 = teamService.getTeamById(team2Id);
        return tossService.conductToss(team1, team2);
    }


}
