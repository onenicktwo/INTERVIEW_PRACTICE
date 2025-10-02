package com.sourceallies.interview.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue
    private Long id;

    @Transient
    private Board board;

    private char currentPlayer;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    public Game() {
        board = new Board();
        status = GameStatus.IN_PROGRESS;
        currentPlayer = 'X';
    }

    public void makeMove(int row, int col) {
        if (status != GameStatus.IN_PROGRESS) {
            throw new IllegalStateException("Game is already over.");
        }

        board.placeMark(row, col, currentPlayer);

        if (board.checkForWinner(currentPlayer)) {
            status = (currentPlayer == 'X') ? GameStatus.X_WINS : GameStatus.O_WINS;
        } else if (board.isFull()) {
            status = GameStatus.DRAW;
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }
}
