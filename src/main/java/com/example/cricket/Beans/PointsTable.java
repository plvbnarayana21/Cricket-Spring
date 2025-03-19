package com.example.cricket.Beans;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "PointsTable")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointsTable {
    @Id
    private String id;

    @Builder.Default
    private List<TeamPoints> teamPoints = new ArrayList<>();

    private String tournamentId;
}