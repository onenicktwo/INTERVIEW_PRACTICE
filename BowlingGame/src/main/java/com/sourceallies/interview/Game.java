package com.sourceallies.interview;

import lombok.*;

@Data
class Game {
    private Bowling bowling;

    public Game() {
        bowling = new Bowling();
    }

    public void makeBowl(int pins) {
        bowling.knockPins(pins);
    }
}