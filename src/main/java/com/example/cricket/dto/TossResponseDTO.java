package com.example.cricket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TossResponseDTO {
    private String tossWinner;
    private String tossOutcome;
    private String choice;
}
