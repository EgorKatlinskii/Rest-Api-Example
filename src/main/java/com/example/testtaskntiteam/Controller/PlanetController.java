package com.example.testtaskntiteam.Controller;

import com.example.testtaskntiteam.Entity.Planet;
import com.example.testtaskntiteam.Service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PlanetController {


    @Autowired
    PlanetService planetService;

    @PostMapping("/savePlanet")
    @ResponseBody
    public ResponseEntity<Object>  savePlanet(@Valid @RequestBody Planet newPlanet){
        return planetService.savePlanet(newPlanet)!= null
                ? ResponseEntity.status(HttpStatus.OK).body(newPlanet)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/deletePlanet/{id}")
    public ResponseEntity<?> deletePlanet(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(planetService.deletePlanet(id));

    }

    @PutMapping("/addLord/{lordId}/{planetId}")
    @ResponseBody
    public ResponseEntity<?> addLordPlanet(@PathVariable int lordId,@PathVariable int planetId){
        return planetService.addLordPlanet(lordId,planetId)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
