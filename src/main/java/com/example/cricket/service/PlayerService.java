package com.example.cricket.service;

import com.example.cricket.dto.PlayerResponseDTO;
import com.example.cricket.Beans.Player;
import com.example.cricket.Beans.Team;
import com.example.cricket.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepo playerRepo;

    @Autowired
    private TeamRepo teamRepo;

    @Transactional
    public synchronized String addPlayer(Player player, String teamName) {
        player = playerRepo.save(player);
        Team team = teamRepo.findByName(teamName).orElse(null);
        if (team == null) {
            team = Team.builder()
                    .name(teamName)
                    .players(new ArrayList<>())
                    .build();
        }
        if (team.getPlayers().size() >= 11) {
            throw new RuntimeException("Team is full with 11 players. Choose another team or no team.");
        }
        team.getPlayers().add(player);
        player.setTeam(team);
        teamRepo.save(team);
        playerRepo.save(player);
        return "Player added successfully to " + teamName;
    }

    @Transactional(readOnly = true)
    public PlayerResponseDTO getPlayerById(String id) {
        Player player = playerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        return new PlayerResponseDTO(
                player.getId(),
                player.getPname(),
                player.getType(),
                player.getRunsScored(),
                player.getBallsFaced(),
                player.getWicketTaken(),
                player.getBallsBowled(),
                player.getRunsConceded()
        );
    }

    @Transactional(readOnly = true)
    public PlayerResponseDTO getPlayerByName(String name) {
        Player player = playerRepo.findByPname(name)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        return new PlayerResponseDTO(
                player.getId(),
                player.getPname(),
                player.getType(),
                player.getRunsScored(),
                player.getBallsFaced(),
                player.getWicketTaken(),
                player.getBallsBowled(),
                player.getRunsConceded()
        );
    }

    @Transactional
    public synchronized void updateBattingPlayer(String name, int runs, int balls) {
        Player player = playerRepo.findByPname(name)
                .orElseThrow(() -> new RuntimeException("Player not found: " + name));

        player.setRunsScored(player.getRunsScored() + runs);
        player.setBallsFaced(player.getBallsFaced() + balls);
        playerRepo.save(player);
    }

    @Transactional
    public synchronized void updateBowlingPlayer(String name, int runsGiven, int ballsBowled, int wicketsTaken) {
        Player player = playerRepo.findByPname(name)
                .orElseThrow(() -> new RuntimeException("Player not found: " + name));

        player.setRunsConceded(player.getRunsConceded() + runsGiven);
        player.setBallsBowled(player.getBallsBowled() + ballsBowled);
        player.setWicketTaken(player.getWicketTaken() + wicketsTaken);
        playerRepo.save(player);
    }

    @Transactional(readOnly = true)
    public List<Player> getPlayersByIds(List<Player> players) {
        List<String> playerIds = players.stream()
                .map(Player::getId)
                .collect(Collectors.toList());
        return playerRepo.findAllById(playerIds);
    }

    @Transactional
    public synchronized void updatePlayersMatchCount(Team team) {
        team.getPlayers().forEach(player -> {
            player.setMatchesPlayed(player.getMatchesPlayed() + 1);
            playerRepo.save(player);
        });
    }

    @Transactional
    public synchronized void memReset(List<Player> players) {
        players.forEach(player -> {
            player.setBallsBowled(0);
            player.setRunsConceded(0);
            player.setRunsScored(0);
            player.setBallsFaced(0);
            player.setWicketTaken(0);
            player.setMatchesPlayed(1);
        });
    }

    @Transactional
    public synchronized void updateAllPlayers() {
        List<Player> players = playerRepo.findAll();
        players.forEach(player -> {
            player.setMatchesPlayed(0);
            player.setRunsConceded(0);
            player.setRunsScored(0);
            player.setWicketTaken(0);
            player.setBallsBowled(0);
            player.setBallsFaced(0);
        });
        playerRepo.saveAll(players);
    }
}