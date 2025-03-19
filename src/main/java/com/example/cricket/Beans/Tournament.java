package com.example.cricket.Beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Tournament")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Tournament {
    @Id
    private String id;

    private String tournamentWinner;
    private String name;

    private List<String> teams;

}