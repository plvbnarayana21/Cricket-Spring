package com.example.cricket.service;

import com.example.cricket.Beans.Match;
import com.example.cricket.Beans.Team;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MatchContext {
    private final Match match;
    private final Team battingTeam;
    private final Team bowlingTeam;

}