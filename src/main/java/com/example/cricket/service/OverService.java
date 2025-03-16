package com.example.cricket.service;

import com.example.cricket.Beans.Innings;
import com.example.cricket.Beans.Match;
import com.example.cricket.Beans.Over;
import com.example.cricket.repository.OverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OverService {

    @Autowired
    private OverRepo overRepo;

    @Transactional
    public synchronized void updateOver(String bowlerName, int overRuns, int overWickets, int oNo, int inningsno, Innings innings, Match match) {
        Over over = Over.builder()
                .runsScored(overRuns)
                .wicketsFallen(overWickets)
                .bowlerName(bowlerName)
                .overNumber(oNo)
                .inningsno(inningsno)
                .innings(innings)
                .match(match)
                .build();

        overRepo.save(over);
    }
}