package com.example.testtaskntiteam.Service;

import com.example.testtaskntiteam.Entity.Lord;
import com.example.testtaskntiteam.Entity.Planet;
import com.example.testtaskntiteam.Repository.LordRepository;
import com.example.testtaskntiteam.Repository.PlanetRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
public class PlanetService {


    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private LordRepository lordRepository;


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

    public boolean addLordPlanet(int lordId, int planetId){
        if(!lordRepository.existsById(lordId)){
            throw new EntityNotFoundException("No Lord!!");
        }else if(!planetRepository.existsById(planetId)){
            throw new EntityNotFoundException("No planet");
        }
        var planet = planetRepository.getById(planetId);
        var planetLord = planet.getLord();
        if(planetLord==null ){
            System.out.println("!!!!");
            planetLord = lordRepository.getById(lordId);
            planet.setLord(planetLord);
            planetRepository.save(planet);
            return true;
        }else {
            throw new EntityNotFoundException("Planet lord is active");
        }
    }

}
