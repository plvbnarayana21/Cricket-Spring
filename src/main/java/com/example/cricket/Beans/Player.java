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

    public Player(Player other) {
        this.id = null; // New clone should have a new ID
        this.pname = other.pname;
        this.type = other.type;
        this.runsScored = other.runsScored;
        this.ballsFaced = other.ballsFaced;
        this.wicketTaken = other.wicketTaken;
        this.ballsBowled = other.ballsBowled;
        this.runsConceded = other.runsConceded;
        this.team = null; // Prevent circular reference
    }
}
