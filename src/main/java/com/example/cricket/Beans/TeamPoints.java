package com.example.cricket.Beans;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TeamPoints {
    private  String teamName;
    private int matchesPlayed;
    private int matchesWon;
    private int Points;
    private int matchesLost;
    private int highestScore;
}
