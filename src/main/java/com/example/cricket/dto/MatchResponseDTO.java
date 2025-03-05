package com.example.cricket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchResponseDTO {

    private String id;
    private String team1Name;
    private String team2Name;
    private int team1Runs;
    private int team2Runs;
    private int team1Wickets;
    private int team2Wickets;
    private int team1BallsFaced;
    private int team2BallsFaced;
    private String winner;
}
