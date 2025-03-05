package com.example.cricket.businessLogic;

import com.example.cricket.Beans.BallType;
import com.example.cricket.Beans.Player;
import com.example.cricket.dto.BallOutcome;
import com.example.cricket.utility.Probability;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope("prototype")
public class ProcessBall {

    @Autowired
    private Probability probability;

    public BallOutcome playBall(Player striker, Player bowler, int ballNumber) {
        BallOutcome outcome = probability.run(striker.getType());
        BallType ballType = outcome.getBallType();
        int runs = outcome.getRuns();

        if (ballType == BallType.WIDE || ballType == BallType.NO_BALL) {
            extras(outcome, bowler, striker, ballNumber);
            return outcome;
        } else {
            stats(outcome, striker, bowler);
            if (runs == 7) {
                wicket(striker, bowler, ballNumber);
            } else {
                runs(outcome, striker, bowler, ballNumber);
            }
        }

        return outcome;
    }

    private void extras(BallOutcome outcome, Player bowler, Player striker, int ballNumber) {
        int runs = outcome.getRuns();
        if (outcome.getBallType() == BallType.WIDE) {
            System.out.println("Ball " + ballNumber + ": Wide Ball! Bowler: " + bowler.getPname() +
                    " | Extra " + runs + " runs scored. Striker: " + striker.getPname());
        } else if (outcome.getBallType() == BallType.NO_BALL) {
            System.out.println("Ball " + ballNumber + ": No Ball.. Free Hit!!!! Bowler: " + bowler.getPname() + " | Extra " + runs + " runs scored. Striker: " + striker.getPname());
        }
    }


    private void stats(BallOutcome outcome, Player striker, Player bowler) {
        int runs = outcome.getRuns();
        bowler.setBallsBowled(bowler.getBallsBowled() + 1);
        striker.setBallsFaced(striker.getBallsFaced() + 1);

        if (runs != 7) {
            striker.setRunsScored(striker.getRunsScored() + runs);
            bowler.setRunsConceded(bowler.getRunsConceded() + runs);
        }
    }

    private void wicket(Player striker, Player bowler, int ballNumber) {
        System.out.println("Ball " + ballNumber + ": WICKET! Bowler: " + bowler.getPname() + " | Batsman: " + striker.getPname() + " is out!");
        bowler.setWicketTaken(bowler.getWicketTaken() + 1);
        striker.setBallsFaced(striker.getBallsFaced()+1);
    }

    private void runs(BallOutcome outcome, Player striker, Player bowler, int ballNumber) {
        int runs = outcome.getRuns();
        System.out.println("Ball " + ballNumber + ": Runs scored: " + runs + " | Striker: " + striker.getPname() + " | Bowler: " + bowler.getPname());
    }
}