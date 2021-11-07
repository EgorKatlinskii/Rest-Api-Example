package com.example.testtaskntiteam.Controller;

import com.example.testtaskntiteam.Entity.Lord;
import com.example.testtaskntiteam.Service.LordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LordController {

    @Autowired
    LordService lordService;

    @PostMapping("/saveLord")
    @ResponseBody
    public ResponseEntity<?> saveLord(@Valid @RequestBody Lord newLord) {
        return lordService.saveLord(newLord) != null
                ? ResponseEntity.status(HttpStatus.OK).body(newLord)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/getTheYoungesLords/{count}")
    @ResponseBody
    public ResponseEntity<List<Lord>> getTheYoungesLords(@PathVariable int count) {
        var listLords = lordService.getTheYoungestLords(count);
        return !listLords.isEmpty()
                ? ResponseEntity.status(HttpStatus.OK).body(listLords)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
