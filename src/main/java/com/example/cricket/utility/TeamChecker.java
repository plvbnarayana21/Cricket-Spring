package com.example.cricket.utility;

import com.example.cricket.Beans.Match;
import com.example.cricket.Beans.Team;
import com.example.cricket.Exception.TeamAlreadyInMatchException;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TeamChecker {
    private final Set<String> teamsInMatch = ConcurrentHashMap.newKeySet();

  public synchronized void addTeams(Match match){


        teamsInMatch.add(match.getTeamA().getName());
        teamsInMatch.add(match.getTeamB().getName());
        String s = "Teams added to checker: " + match.getTeamA().getName() + " " + match.getTeamB().getName();
        System.out.println(s);
    }

    public synchronized boolean checkTeams(Team teamA,Team teamB) throws TeamAlreadyInMatchException{
        if (teamsInMatch.contains(teamA.getName())) {
//            throw new TeamAlreadyInMatchException(teamA.getName() + " is already in a match.");
            System.out.println(teamA.getName() + " is already in a match.");
            return false;
        }
        if (teamsInMatch.contains(teamB.getName())) {
//            throw new TeamAlreadyInMatchException(teamB.getName() + " is already in a match.");
            System.out.println(teamB.getName() + " is already in a match.");
            return false;
        }
        return true;
    }

    public synchronized void removeTeams(Team teamA, Team teamB) {
        teamsInMatch.remove(teamA.getName());
        teamsInMatch.remove(teamB.getName());
        System.out.println("Teams removed from checker: "+ teamA.getName()+" "+ teamB.getName());
    }
}