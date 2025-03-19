package com.example.cricket.Beans;

import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

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
