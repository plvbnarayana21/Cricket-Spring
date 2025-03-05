
package com.example.cricket.Beans;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "innings")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Innings {
    @Id
    private String id;

    @DBRef
    private Team battingTeam;

    @DBRef
    private Team bowlingTeam;

    @DBRef
    private ScoreCard Scorecard;

    private int totalRuns;
    private int wicketsLost;
    private int ballsPlayed;
    private int oversToPlay;

    @DBRef
    private Match match;

    private int iNo;

    private String scoreboardId;

}