package com.sourceallies.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    void testCreateGameEmpty() {
        Game game = new Game();
        assertTrue(game.getBowling().isEmpty());
    }

    @Test
    void testGameMakeBowl() {
        Game game = new Game();
        game.makeBowl(5);

        assertEquals(1, game.getBowling().getFrames()[0].getRolls().size());
        assertEquals(0, game.getBowling().getFrameIndex());

        game.makeBowl(4);

        assertEquals(1, game.getBowling().getFrameIndex());
        assertEquals(9, game.getBowling().getScoreAtIndex(0));
    }

    @Test
    void testGameIsFull() {
        Game game = new Game();
        for (int i = 0; i < 20; i++) {
            game.makeBowl(0);
        }
        assertTrue(game.getBowling().isDone());
    }

    @Test
    void testGameSpare() {
        Game game = new Game();
        game.makeBowl(5);
        game.makeBowl(5);

        game.makeBowl(5);

        assertEquals(15, game.getBowling().getScoreAtIndex(0));
    }

    @Test
    void testGameStrike() {
        Game game = new Game();
        game.makeBowl(10);
        game.makeBowl(5);
        game.makeBowl(4);

        assertEquals(19, game.getBowling().getScoreAtIndex(0));
    }

    @Test
    void testGameStrikesInARow() {
        Game game = new Game();
        game.makeBowl(10);
        game.makeBowl(10);
        game.makeBowl(10);
        game.makeBowl(5);
        game.makeBowl(4);

        assertEquals(30, game.getBowling().getScoreAtIndex(0));
    }
}
