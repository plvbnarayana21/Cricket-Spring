package com.example.cricket.service;

import com.example.cricket.Beans.Player;
import com.example.cricket.Beans.Team;
import com.example.cricket.dto.PlayerResponseDTO;
import com.example.cricket.dto.TeamResponseDTO;
import com.example.cricket.repository.PlayerRepo;
import com.example.cricket.repository.PlayerRepository;
import com.example.cricket.repository.TeamRepo;
//import com.example.cricket.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {
//    @Autowired
//    private TeamRepository teamRepository;
    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamRepo teamRepo;
//    @Autowired
//    private PlayerRepository playerRepository;

    @Autowired
    private PlayerRepo playerRepo;

    public TeamResponseDTO getTeamByName(String name) {
        Team team = teamRepo.findByName(name)
                .orElseThrow(() -> new RuntimeException("Team not found: " + name));

        return new TeamResponseDTO(
                team.getId(),
                team.getName(),
                team.getPlayers().stream().map(player -> new PlayerResponseDTO(
                        player.getId(),
                        player.getPname(),
                        player.getType(),
                        player.getRunsScored(),
                        player.getBallsFaced(),
                        player.getWicketTaken(),
                        player.getBallsBowled(),
                        player.getRunsConceded()
                )).collect(Collectors.toList())
        );
    }

    public TeamResponseDTO TeamById(String teamId) {
        Team team = teamRepo.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        return new TeamResponseDTO(
                team.getId(),
                team.getName(),
                team.getPlayers().stream().map(player -> new PlayerResponseDTO(
                        player.getId(),
                        player.getPname(),
                        player.getType(),
                        player.getRunsScored(),
                        player.getBallsFaced(),
                        player.getWicketTaken(),
                        player.getBallsBowled(),
                        player.getRunsConceded()
                )).collect(Collectors.toList())
        );
    }

    public Team getTeamById(String teamId) {
        return teamRepo.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));
    }

    public Team getByname(String name){
        Team team = teamRepo.findByName(name)
                .orElseThrow(() -> new RuntimeException("Team " + name + " not found"));
return team;
    }


    public List<TeamResponseDTO> getAllTeams() {
        List<Team> teams = teamRepo.findAll();

        return teams.stream().map(team -> new TeamResponseDTO(
                team.getId(),
                team.getName(),
                team.getPlayers().stream().map(player -> new PlayerResponseDTO(
                        player.getId(),
                        player.getPname(),
                        player.getType(),
                        player.getRunsScored(),
                        player.getBallsFaced(),
                        player.getWicketTaken(),
                        player.getBallsBowled(),
                        player.getRunsConceded()
                )).collect(Collectors.toList())
        )).collect(Collectors.toList());
    }
}
