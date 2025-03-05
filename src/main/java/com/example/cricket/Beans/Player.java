package com.example.cricket.Beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "players")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {
    @Id
    private String id;
    private String pname;
    private String type;
    private Integer runsScored;
    private Integer ballsFaced;
    private Integer wicketTaken;
    private Integer ballsBowled;
    private Integer runsConceded;
    private Integer matchesPlayed;

    @DBRef
    @JsonBackReference
    private Team team;
}
