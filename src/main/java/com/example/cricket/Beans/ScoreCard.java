package com.example.cricket.Beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "ScoreCard")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreCard {
    @Id
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
