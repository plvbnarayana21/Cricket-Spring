package com.example.cricket.service;

import com.example.cricket.Beans.Innings;
import com.example.cricket.Beans.Match;
import com.example.cricket.Beans.Over;
import com.example.cricket.repository.OverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OverService {

//    @Autowired
//    private OverRepository overRepository;

    @Autowired
    private OverRepo overRepo;

    public void updateOver(String bowlerName, int overRuns, int overWickets,int oNo,int innningsno,Innings innings,Match match) {
        Over over= Over.builder().runsScored(overRuns).wicketsFallen(overWickets).bowlerName(bowlerName).
                overNumber(oNo).inningsno(innningsno).innings(innings).match(match).build();
        overRepo.save(over);
//        update(over);
    }
}