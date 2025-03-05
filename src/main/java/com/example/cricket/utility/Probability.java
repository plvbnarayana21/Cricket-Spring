package com.example.cricket.utility;

import com.example.cricket.Beans.BallType;
import com.example.cricket.dto.BallOutcome;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Probability {
    private final Random rand = new Random();

    public BallOutcome run(String type) {
        int[] normalRuns = {};
        int[] wideRuns = {};
        int[] noBallRuns = {};
        if ("batsman".equalsIgnoreCase(type)) {
            normalRuns = new int[]{0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 4, 4, 6, 6, 7, 7};
            wideRuns = new int[]{0, 1, 2};
            noBallRuns = new int[]{0, 0, 1, 2, 2, 3, 4, 4, 4, 6, 6};
        } else {
            normalRuns = new int[]{0, 0, 0, 1, 1, 1, 1, 2, 2, 4, 6, 7};
            wideRuns = new int[]{0, 1};
            noBallRuns = new int[]{0, 1, 1, 1, 1, 2, 2, 2, 4, 6};
        }
        int randomValue = rand.nextInt(100);
        if (randomValue < 5) return new BallOutcome(BallType.WIDE, wideRuns[rand.nextInt(wideRuns.length)]);
        if (randomValue < 10) return new BallOutcome(BallType.NO_BALL, noBallRuns[rand.nextInt(noBallRuns.length)]);

        int runScored = normalRuns[rand.nextInt(normalRuns.length)];
        if (runScored == 7) return new BallOutcome(BallType.NORMAL, 7);
        return new BallOutcome(BallType.NORMAL, runScored);
    }
}