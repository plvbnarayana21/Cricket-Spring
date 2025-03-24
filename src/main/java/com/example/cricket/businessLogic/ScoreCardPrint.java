
package com.example.cricket.businessLogic;

import com.example.cricket.Beans.*;
import com.example.cricket.Exception.ScoreCardNotFoundException;
import com.example.cricket.dto.ScoreCardDTO;
import com.example.cricket.repository.ScoreCardRepo;
import com.example.cricket.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ScoreCardPrint {

    private final ScoreCardRepo scoreCardRepo;
    private final PlayerService playerService;

    @Transactional
    public void update(List<Player> batting, List<Player> bowling, int runs, int balls, int wickets, Innings innings, Match match, int iNo) {
        batting.forEach(player -> {
            synchronized (player) {
//                System.out.printf("%-15s %-10d %-10d%n", player.getPname(), player.getRunsScored(), player.getBallsFaced());
//               playerService.updatePlayersMatchCount();
                playerService.updateBattingPlayer(player.getPname(), player.getRunsScored(), player.getBallsFaced());
            }
        });

        bowling.stream()
                .filter(player -> player.getBallsBowled() > 0)
                .forEach(player -> {
                    synchronized (player) {
                        playerService.updateBowlingPlayer(player.getPname(), player.getRunsConceded(), player.getBallsBowled(), player.getWicketTaken());
//                        System.out.printf(
//                                "%-15s %-10s %-10d %-10d%n",
//                                player.getPname(),
//                                over(player.getBallsBowled()),
//                                player.getRunsConceded(),
//                                player.getWicketTaken()
//                        );
                    }
                });


        ScoreCard scoreCard = ScoreCard.builder()
                .batting(batting)
                .bowling(bowling)
                .totalRuns(runs)
                .wickets(wickets)
                .ballsplayed(balls)
                .inning(innings.getId())
                .match(match.getId())
                .iNo(iNo)
                .build();

        scoreCardRepo.save(scoreCard);
  }

    public ScoreCardDTO getthruId(String id) {
        Optional<ScoreCard> scoreCardOptional = scoreCardRepo.findById(id);
        if (scoreCardOptional.isEmpty()) {
            throw new ScoreCardNotFoundException("Scorecard not found with ID: " + id);
        }

        ScoreCard scoreCard = scoreCardOptional.get();
        return mapToScoreCardDTO(scoreCard);
    }

    private ScoreCardDTO mapToScoreCardDTO(ScoreCard scoreCard) {
        return ScoreCardDTO.builder()
                .id(scoreCard.getId())
                .batting(scoreCard.getBatting())
                .bowling(scoreCard.getBowling())
                .totalRuns(scoreCard.getTotalRuns())
                .wickets(scoreCard.getWickets())
                .ballsplayed(scoreCard.getBallsplayed())
                .inning(scoreCard.getInning())
                .match(scoreCard.getMatch())
                .iNo(scoreCard.getINo())
                .build();
    }
}