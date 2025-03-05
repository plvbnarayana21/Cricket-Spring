package com.example.cricket.businessLogic;

import com.example.cricket.Beans.*;
import com.example.cricket.dto.BallOutcome;
import com.example.cricket.service.OverService;
import com.example.cricket.service.ScoreCardService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.List;
@Data
@Component
@Scope("prototype")
public class Inning {

    @Autowired
    private OverService overService;

    @Autowired
    private ScoreCardPrint scoreCardPrint;

    @Autowired
    private ScoreCardService scoreCardService;

    @Autowired
    private ProcessBall processBall;

    private List<Player> battingTeam;
    private List<Player> bowlingTeam;
    private int totalRuns = 0;
    private int wicketsLost = 0;
    private int ballsBowled = 0;
    private Player striker;
    private Player nonStriker;
    private int bowlerIndex = 10;
    private int overWickets = 0, overRuns = 0, overnumber = 1;
    private Innings innings;
    private int iNo;
    private Match match;

    public void init(List<Player> battingTeam, List<Player> bowlingTeam, Innings innings, Match match, int iNo) {
        this.battingTeam = battingTeam;
        this.innings = innings;
        this.iNo = iNo;
        this.match = match;
        this.bowlingTeam = bowlingTeam;
        if (battingTeam.size() >= 2) {
            this.striker = battingTeam.get(0);
            this.nonStriker = battingTeam.get(1);
        } else {
            throw new RuntimeException("Batting team size is not enough ");
        }
    }


    public int startInnings(int maxOvers, int target) {
        int flag = 0;
        boolean isFreeHit = false;

        while (ballsBowled < maxOvers * 6 && wicketsLost < 10) {
            Player bowler = bowlingTeam.get(bowlerIndex);
            System.out.println("\n=== Over " + overnumber + " ===");
            System.out.println("Bowler: " + bowler.getPname());
            bowler.setMatchesPlayed(1);

            overRuns = 0;
            overWickets = 0;

            for (int ball = 1; ball <= 6; ball++) {
                if (wicketsLost >= 10) break;

                BallOutcome outcome = processBall.playBall(striker, bowler, ball);

                if (isFreeHit) {
                    if (outcome.getRuns() == 7) {
                        System.out.println("Ball " + ball + ": Wicket on Free Hit!! Not Out");
                        striker.setBallsFaced(striker.getBallsFaced()-1);
                        bowler.setWicketTaken(bowler.getWicketTaken()-1);
                        outcome = new BallOutcome(BallType.NORMAL, 0);
                    }
                    isFreeHit = false;
                }

                if (outcome.getBallType() == BallType.WIDE || outcome.getBallType() == BallType.NO_BALL) {
                    int extraRuns = outcome.getRuns() + 1;
                    bowler.setRunsConceded(bowler.getRunsConceded()+extraRuns);
                    striker.setRunsScored(striker.getRunsScored()+extraRuns-1);
                    totalRuns += extraRuns;
                    overRuns += extraRuns;

                    if (outcome.getBallType() == BallType.NO_BALL) {
                        isFreeHit = true;
                    }

                    ball--;
                    continue;
                }
                ballsBowled++;
                if (outcome.getRuns() == 7) {
                    wicketsLost++;
                    overWickets++;
                    striker.setBallsFaced(striker.getBallsFaced()-1);
                    if (wicketsLost + 1 < battingTeam.size()) {
                        striker = battingTeam.get(wicketsLost + 1);
                    }
                } else {
                    totalRuns += outcome.getRuns();
                    overRuns += outcome.getRuns();
                    if (outcome.getRuns() % 2 == 1) {
                        strikeChange();
                    }
                }

                if (totalRuns >= target) {
                    flag = 1;
                    break;
                }
            }

            strikeChange();
            System.out.println("Over Summary | Runs: " + overRuns + " | Wickets: " + overWickets);
            overService.updateOver(bowler.getPname(), overRuns, overWickets, overnumber++, iNo, innings, match);

            if (flag == 1) break;
            bowlerIndex = (bowlerIndex + 10) % bowlingTeam.size();
        }

        if (flag == 1)
            System.out.println("\nTarget achieved!!!!");

        System.out.println("\nInnings over! Score: " + totalRuns + "/" + wicketsLost);
        System.out.println("Balls Played: " + ballsBowled);

        scoreCardPrint.displayScore(battingTeam, bowlingTeam, totalRuns, wicketsLost, ballsBowled);
        scoreCardService.update(battingTeam, bowlingTeam, totalRuns, ballsBowled, wicketsLost, innings, match, iNo);
        return totalRuns;
    }

    private void strikeChange() {
        Player temp = striker;
        striker = nonStriker;
        nonStriker = temp;
    }
}