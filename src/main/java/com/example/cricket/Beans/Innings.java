//package com.example.cricket.Beans;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.DBRef;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Document(collection = "innings")
//public class Innings {
//    @Id
//    private String id;
//
//    @DBRef(lazy = true)
//    private Team battingTeam;
//
//    @DBRef(lazy = true)
//    private Team bowlingTeam;
//
//    private int iNo;
//    private int totalRuns;
//    private int wicketsLost;
//    private int ballsPlayed;
//    private int oversToPlay;
//
//    @DBRef(lazy = true)
//    private Match match;
//}

package com.example.cricket.Beans;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

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