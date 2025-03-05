package com.example.cricket.controller;

import com.example.cricket.dto.ScoreCardDTO;
import com.example.cricket.service.ScoreCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scorecard")
public class ScoreCardController {

    @Autowired
    private ScoreCardService scoreCardService;

    @GetMapping("/{id}")
    public ScoreCardDTO getbyId(@PathVariable String id){
        return scoreCardService.getthruId(id);
    }
}
