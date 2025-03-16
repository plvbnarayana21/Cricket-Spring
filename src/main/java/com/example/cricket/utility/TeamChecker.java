
package com.example.cricket.utility;

import com.example.cricket.Beans.Team;
import com.example.cricket.Exception.TeamAlreadyInMatchException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TeamChecker {
    private final Set<String> teamsInMatch = new HashSet<>();

    public synchronized void checkAndAddTeams(Team teamA, Team teamB) throws TeamAlreadyInMatchException {
        String team1 = teamA.getName();
        String team2 = teamB.getName();

        if (teamsInMatch.contains(team1) || teamsInMatch.contains(team2)) {
            throw new TeamAlreadyInMatchException("One or both teams are already in a match.");
        }
        teamsInMatch.add(team1);
        teamsInMatch.add(team2);

//        System.out.println();
//        for(String t:teamsInMatch){
//            System.out.println(t);
//        }
//        System.out.println();
    }

    public synchronized void removeTeams(Team teamA, Team teamB) {
        String team1 = teamA.getName();
        String team2 = teamB.getName();

        teamsInMatch.remove(team1);
        teamsInMatch.remove(team2);
//        System.out.println();
//        for(String t:teamsInMatch){
//            System.out.println(t);
//        }
//        System.out.println();

    }
}