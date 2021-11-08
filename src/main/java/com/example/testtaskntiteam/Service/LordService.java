package com.example.testtaskntiteam.Service;

import com.example.testtaskntiteam.Entity.Lord;
import com.example.testtaskntiteam.Entity.Planet;
import com.example.testtaskntiteam.Repository.LordRepository;
import com.example.testtaskntiteam.Repository.PlanetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.ParameterOutOfBoundsException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
@Slf4j
public class LordService {

    @Autowired
    private LordRepository lordRepository;


    public Lord saveLord(Lord newLord) {
        lordRepository.save(newLord);
        log.info("Create new lord - {}", newLord);
        return newLord;
    }

    public List<Lord> getTheYoungestLords(int count) {
        if (count > 0) {
            var lords = lordRepository.getTheYoungestLords(count);
            log.info("TOP 10 youngest overlords - {}", lords);
            return lords;
        }
        else{
            throw new IndexOutOfBoundsException("The number of overlords sought must be greater than zero");
        }
    }

    public List<Lord> getIdlers() {
        return lordRepository.getIdlers();
    }

    public List<Lord> getAllLords(){
        return lordRepository.findAll();
    }

}
