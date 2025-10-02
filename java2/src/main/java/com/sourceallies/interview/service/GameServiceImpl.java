package com.sourceallies.interview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourceallies.interview.model.Game;
import com.sourceallies.interview.repository.GameRepository;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game createGame() {
        return gameRepository.save(new Game());
    }

}
