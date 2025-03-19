package com.example.cricket.dto;

import com.example.cricket.Beans.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
@Builder
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
