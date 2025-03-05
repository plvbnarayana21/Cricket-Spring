package com.example.cricket.dto;

import com.example.cricket.Beans.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
public class TossResult {
    private final Team tossWinner;
    private final String choice;

}