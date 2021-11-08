package com.example.testtaskntiteam.Service;

import com.example.testtaskntiteam.Entity.Lord;
import com.example.testtaskntiteam.Repository.LordRepository;
import com.example.testtaskntiteam.Repository.PlanetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
@Slf4j
public class LordService {

    @Autowired
    private LordRepository lordRepository;


    @Autowired
    private PlanetRepository planetRepository;


    public Lord saveLord(Lord newLord){
        lordRepository.save(newLord);
        log.info("Create new lord - {}",newLord);
        return newLord;
    }

    public List<Lord> getTheYoungestLords(int count){
        if(count > 0){
            var lords = lordRepository.getTheYoungestLords(count);
            log.info("TOP 10 youngest overlords - {}",lords);
            return lords;
        }
        return Collections.emptyList();

    }


}
