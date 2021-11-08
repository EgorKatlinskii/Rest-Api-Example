package com.example.testtaskntiteam.Controller;

import com.example.testtaskntiteam.Entity.Lord;
import com.example.testtaskntiteam.Repository.LordRepository;
import com.example.testtaskntiteam.Service.LordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.AbstractMap;
import java.util.List;

@RestController
public class LordController {

    @Autowired
    LordService lordService;

    @Autowired
    LordRepository lordRepository;

    @PostMapping("/saveLord")
    @ResponseBody
    public ResponseEntity<?> saveLord(@Valid @RequestBody Lord newLord) {
        return lordService.saveLord(newLord) != null
                ? ResponseEntity.status(HttpStatus.OK).body(newLord)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/getYoungestLords/{count}")
    @ResponseBody
    public ResponseEntity<List<Lord>> getYoungestLords(@PathVariable int count) {
        var listLords = lordService.getTheYoungestLords(count);
        return !listLords.isEmpty()
                ? ResponseEntity.status(HttpStatus.OK).body(listLords)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/getIdlers")
    @ResponseBody
    public ResponseEntity<?> getIdlers(){
        var listIdlers = lordService.getIdlers();
        return !listIdlers.isEmpty()
                ? ResponseEntity.status(HttpStatus.OK).body(listIdlers)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(new AbstractMap.SimpleEntry<>("Message:", "No loafers!"));
    }

    @GetMapping("/getLords")
    @ResponseBody
    public ResponseEntity<List<Lord>> getLords(){
        var listLords =lordService.getAllLords();
        return !listLords.isEmpty()
                ? ResponseEntity.status(HttpStatus.OK).body(listLords)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
