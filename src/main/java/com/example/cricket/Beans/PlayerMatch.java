//package com.example.cricket.Beans;
//
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.DBRef;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//@Data
//@AllArgsConstructor
//@Builder
//@NoArgsConstructor
//@Document(collection ="PlayerMatch")
//public class PlayerMatch {
//
//    @Id
//    private String id;
//
//    private String player;
//    private String matchId;
//    private String name;
//    private String TeamName;
//    private boolean wicketLost;
//    private int runsScored;
//    private int runsConceded;
//    private int ballsFaced;
//    private int ballsBowled;
//    private int wicketsTaken;
//
//
//}
package com.example.cricket.Beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Document(collection = "PlayerMatch")
public class PlayerMatch {

    @Id
    private String id;

    private String name;
    private String matchId;
    private String TeamName;
    private boolean wicketLost;
    private int runsScored;
    private int runsConceded;
    private int ballsFaced;
    private int ballsBowled;
    private int wicketsTaken;

}