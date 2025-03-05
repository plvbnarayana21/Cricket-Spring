package com.example.cricket.dto;

import com.example.cricket.Beans.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreCardDTO {
    private String id;
    private List<Player> batting;
    private List<Player> bowling;
    private int totalRuns;
    private int wickets;
    private int ballsplayed;
    private String inning;
    private String match;
    private int iNo;
}
