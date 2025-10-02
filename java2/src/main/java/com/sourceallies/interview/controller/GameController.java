package com.sourceallies.interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sourceallies.interview.model.Game;
import com.sourceallies.interview.service.GameService;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/games")
    public ResponseEntity<Game> createGame() {
        Game game = gameService.createGame();
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }
}
