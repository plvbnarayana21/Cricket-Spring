package com.example.cricket.controller;


import com.example.cricket.Beans.Innings;
import com.example.cricket.dto.InningsDTO;
import com.example.cricket.dto.PlayerResponseDTO;
import com.example.cricket.service.InningsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Innings")
public class InningsController {

    @Autowired
    private InningsService inningsService;

    @GetMapping("/{matchId}/{iNo}")
    @ResponseStatus(HttpStatus.OK)
    public InningsDTO getbyId(@PathVariable String matchId, @PathVariable int iNo)
    {
        return inningsService.getByMatchIdAndINo(matchId,iNo);
    }

}
