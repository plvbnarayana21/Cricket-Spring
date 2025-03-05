package com.example.cricket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResponseDTO {
    private String id;
    private String pname;
    private String type;
    private Integer runsScored;
    private Integer ballsFaced;
    private Integer wicketTaken;
    private Integer ballsBowled;
    private Integer runsConceded;
}
