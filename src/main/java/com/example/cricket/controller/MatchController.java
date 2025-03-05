//got an error for api http://localhost:8080/match/67c540f11444603aa48e86d4 withh error being threads.TaskThread$WrappingRunnable.run(TaskThread.java:63)\n\tat java.base/java.lang.Thread.run(Thread.java:1575)\n",
//        "message": "No static resource match/67c540f11444603aa48e86d4.",
//        "path": "/match/67c540f11444603aa48e86d4"

package com.example.cricket.controller;

import com.example.cricket.Beans.Match;
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
    public ResponseEntity<String> startmatch (@RequestParam String team1Name,@RequestParam String team2Name){
        return matchService.startMatch(team1Name,team2Name);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MatchResponseDTO> getById(@PathVariable String id) {
        MatchResponseDTO matchResponse = matchService.getById(id);
        return ResponseEntity.ok(matchResponse);
    }
}
