package com.example.cricket.dto;

import com.example.cricket.Beans.BallType;
import com.example.cricket.utility.Probability;
import lombok.*;

@Data
@AllArgsConstructor
public class BallOutcome {
    private BallType ballType;
    private int runs;

//    public BallOutcome(Probability.BallType ballType, int wideRun) {
//    }
}
