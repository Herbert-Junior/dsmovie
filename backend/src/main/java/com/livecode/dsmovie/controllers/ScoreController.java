package com.livecode.dsmovie.controllers;

import com.livecode.dsmovie.dto.MovieDTO;
import com.livecode.dsmovie.dto.ScoreDTO;
import com.livecode.dsmovie.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/scores")
public class ScoreController {

    @Autowired
    private ScoreService service;


    @PutMapping
    public MovieDTO saveScore(@RequestBody ScoreDTO dto){

        MovieDTO movieDTO = service.saveScore(dto);
        return movieDTO;
    }
}
