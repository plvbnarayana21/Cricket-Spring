package com.example.cricket.controller;

import com.example.cricket.dto.PlayerResponseDTO;
import com.example.cricket.Beans.Player;
import com.example.cricket.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String addPlayer(@RequestBody Player player, @RequestParam String teamName) {
        return playerService.addPlayer(player, teamName);
    }
    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlayerResponseDTO getPlayerbyID(@PathVariable String id)
    {
        return playerService.getPlayerById(id);
    }

    @PutMapping("updateAll")
    public String updation(){
        playerService.updateAllPlayers();
        return "all Players updated";
    }

}
