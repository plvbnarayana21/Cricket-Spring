package com.example.cricket.service;

import com.example.cricket.dto.TossResponseDTO;
import com.example.cricket.Beans.Team;
import com.example.cricket.dto.TossResult;
import com.example.cricket.repository.TeamRepo;
import com.example.cricket.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class TossService {
//    @Autowired
//    private TeamRepository teamRepository;

    @Autowired
    private TeamRepo teamRepo;

    public TossResult conductToss(Team teamA, Team teamB) {
        if (teamA == null || teamB == null) {
            throw new IllegalArgumentException("Both teams must be valid");
        }

        Random random = new Random();
        Team tossWinner = random.nextBoolean() ? teamA : teamB;
        String choice = random.nextBoolean() ? "bat" : "bowl";

        return new TossResult(tossWinner, choice);
    }

public TossResponseDTO conductToss(String teamAName, String teamBName, String tossCaller, String tossCall, String choice) {
    Optional<Team> teamAOpt = teamRepo.findByName(teamAName);
    Optional<Team> teamBOpt = teamRepo.findByName(teamBName);

    if (teamAOpt.isEmpty() || teamBOpt.isEmpty()) {
        throw new RuntimeException("One or both teams do not exist.");
    }

    Team teamA = teamAOpt.get();
    Team teamB = teamBOpt.get();

    if (!tossCaller.equalsIgnoreCase(teamA.getName()) && !tossCaller.equalsIgnoreCase(teamB.getName())) {
        throw new RuntimeException("Invalid toss caller.");
    }
    if (!tossCall.equalsIgnoreCase("heads") && !tossCall.equalsIgnoreCase("tails")) {
        throw new RuntimeException("Invalid toss call. Choose 'heads' or 'tails'.");
    }

    Random rand = new Random();
    boolean tossResultIsHeads = rand.nextBoolean();
    String tossResult = tossResultIsHeads ? "heads" : "tails";

    String tossWinner = tossCall.equalsIgnoreCase(tossResult) ? tossCaller
            : (tossCaller.equalsIgnoreCase(teamA.getName()) ? teamB.getName() : teamA.getName());

    return new TossResponseDTO(tossWinner, tossResult, choice);
}

}


