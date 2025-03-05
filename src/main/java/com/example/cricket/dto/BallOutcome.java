package com.example.cricket.dto;

import com.example.cricket.Beans.BallType;
import lombok.*;

@Data
@AllArgsConstructor
public class BallOutcome {
    private BallType ballType;
    private int runs;

}
