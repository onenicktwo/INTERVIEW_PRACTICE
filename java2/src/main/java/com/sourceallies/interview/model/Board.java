package com.sourceallies.interview.model;

import lombok.Data;

@Data
public class Board {
    private char[][] cells = new char[3][3];

    public boolean isEmpty() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (cells[r][c] != '\u0000')
                    return false;
            }
        }
        return true;
    }

    public boolean isFull() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (cells[r][c] == '\u0000')
                    return false;
            }
        }
        return true;
    }

    public void placeMark(int row, int col, char mark) {
        if (cells[row][col] != '\u0000') {
            throw new IllegalStateException("Space already occupied.");
        }
        cells[row][col] = mark;
    }

    public char getCell(int row, int col) {
        return cells[row][col];
    }

    public boolean checkForWinner(char currentPlayer) {
        // row
        for (int i = 0; i < 3; i++) {
            if (currentPlayer == cells[i][0] &&
                    cells[i][0] == cells[i][1] &&
                    cells[i][1] == cells[i][2])
                return true;
        }

        // col
        for (int i = 0; i < 3; i++) {
            if (currentPlayer == cells[0][i] &&
                    cells[0][i] == cells[1][i] &&
                    cells[1][i] == cells[2][i])
                return true;
        }

        // diagonals
        if (currentPlayer == cells[0][0] &&
                cells[0][0] == cells[1][1] &&
                cells[1][1] == cells[2][2])
            return true;
        if (currentPlayer == cells[0][2] &&
                cells[0][2] == cells[1][1] &&
                cells[1][1] == cells[2][0])
            return true;

        return false;
    }
}
