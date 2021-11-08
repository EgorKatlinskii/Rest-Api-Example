package com.example.testtaskntiteam.Service;

import com.example.testtaskntiteam.Entity.Planet;
import com.example.testtaskntiteam.Repository.PlanetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PlanetService {


    @Autowired
    private PlanetRepository planetRepository;


    public Planet savePlanet(Planet newPlanet){
        planetRepository.save(newPlanet);
        log.info("Create new planet- {}",newPlanet);
        return newPlanet;
    }

    public boolean deletePlanet(int id){
        if(planetRepository.existsById(id)){
            planetRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
