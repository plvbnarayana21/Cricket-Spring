package com.example.cricket.dto;

import com.example.cricket.Beans.Match;
import com.example.cricket.Beans.ScoreCard;
import com.example.cricket.Beans.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InningsDTO {
    private String id;
    private String battingTeam;
    private String bowlingTeam;
    private int totalRuns;
    private int wicketsLost;
    private int ballsPlayed;
    private int oversToPlay;
    private String match;
    private int iNo;


}
