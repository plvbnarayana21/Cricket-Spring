package com.example.cricket.service;

import com.example.cricket.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteAllService {

    private final MatchRepo matchRepo;
    private final InningsRepo inningsRepo;
    private  final ScoreCardRepo scoreCardRepo;
    private final OverRepo overRepo;
    private final PlayerService playerService;

    public String deleteAll(){

        matchRepo.deleteAll();
        inningsRepo.deleteAll();
        scoreCardRepo.deleteAll();
        overRepo.deleteAll();
        playerService.updateAllPlayers();
        return "Data Updated to 0"  ;

    }

}
