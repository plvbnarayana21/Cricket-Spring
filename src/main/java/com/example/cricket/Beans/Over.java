package com.example.cricket.Beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Over")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Over {
    @Id
    private String id;

    @DBRef(lazy = true)
    private Innings innings;

    @DBRef
    private Match match;

    private int inningsno;
    private String bowlerName;
    private int runsScored;
    private int wicketsFallen;
    private int overNumber;

}