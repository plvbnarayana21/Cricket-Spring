package com.example.cricket.Beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.*;

@Document(collection = "matches")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Match {
    @Id
    private String id;
    private Team teamA;
    private Team teamB;
    private String tossWinner;
    private String tossChoice;
    private Innings firstInnings;
    private Innings secondInnings;
    private String matchWinner;
    private String tournamentId;
    private String location;
    private long tId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String matchType;
    private int highestScore;

    private String status="PENDING";
    @DBRef(lazy = true)
    private List<Innings> inningsList;

}