package com.example.cricket.businessLogic;

import com.example.cricket.Beans.Player;
import com.example.cricket.service.PlayerService;
import com.example.cricket.service.ScoreCardService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreCardPrint {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ScoreCardService scoreCardService;


    public void displayScore(List<Player> battingPly, List<Player> bowlingPly, int totalRuns, int wickets, int balls) {
        System.out.println("\n=== Batting Scorecard ===");
        System.out.printf("%-15s %-10s %-10s%n", "Player", "Runs", "Balls");

        battingPly.forEach(player -> {
            System.out.printf("%-15s %-10d %-10d%n", player.getPname(), player.getRunsScored(), player.getBallsFaced());
            playerService.updateBattingPlayer(player.getPname(), player.getRunsScored(), player.getBallsFaced());
  });

        System.out.println("\n=== Bowling Scorecard ===");
        System.out.printf("%-15s %-10s %-10s %-10s%n", "Bowler", "Overs", "Runs", "Wickets");

        bowlingPly.stream()
                .filter(player -> player.getBallsBowled() > 0)
                .forEach(player -> {
                    playerService.updateBowlingPlayer(player.getPname(), player.getRunsConceded(), player.getBallsBowled(), player.getWicketTaken());
                    System.out.printf(
                            "%-15s %-10s %-10d %-10d%n",
                            player.getPname(),
                            over(player.getBallsBowled()),
                            player.getRunsConceded(),
                            player.getWicketTaken()
                    );
                });

        System.out.println("\nTotal Runs: " + totalRuns + " | Wickets: " + wickets + "/10" + " | BallsFaced: " + balls);
  }

    private static String over(int ballsBowled) {
        return (ballsBowled / 6) + "." + (ballsBowled % 6);
    }
}
