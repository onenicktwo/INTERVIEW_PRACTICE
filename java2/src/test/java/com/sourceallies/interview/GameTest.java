package com.sourceallies.interview;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.sourceallies.interview.model.Game;
import com.sourceallies.interview.model.GameStatus;

public class GameTest {

    @Test
    public void testNewGameIsEmpty() {
        Game game = new Game();
        boolean isBoardEmpty = game.getBoard().isEmpty();
        assertTrue(isBoardEmpty);
    }

    @Test
    public void testMoveUpdatesBoard() {
        Game game = new Game();
        game.makeMove(2, 2);
        boolean isBoardUpdated = game.getBoard().getCell(2, 2) == 'X';
        boolean isNextTurn = game.getCurrentPlayer() == 'O';
        assertTrue(isBoardUpdated);
        assertTrue(isNextTurn);
    }

    @Test
    public void testGameWin() {
        Game game = new Game();
        game.makeMove(0, 0);
        game.makeMove(1, 1);
        game.makeMove(0, 1);
        game.makeMove(2, 2);
        game.makeMove(0, 2);
        boolean isXWin = game.getStatus() == GameStatus.X_WINS;
        assertTrue(isXWin);
    }

    @Test
    public void testGameDraw() {
        Game game = new Game();
        game.makeMove(0, 0);
        game.makeMove(0, 1);
        game.makeMove(1, 1);
        game.makeMove(2, 2);
        game.makeMove(2, 1);
        game.makeMove(0, 2);
        game.makeMove(1, 2);
        game.makeMove(1, 0);
        game.makeMove(2, 0);
        boolean isDraw = game.getStatus() == GameStatus.DRAW;
        assertTrue(isDraw);
    }
}
