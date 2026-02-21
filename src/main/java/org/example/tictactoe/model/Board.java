package org.example.tictactoe.model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private char[][] board;

    public Board(char[][] board) {
        this.board = board;
    }


    // Find alle ledige felter: //
    public List<Move> getAvailableMoves() {
        List<Move> moves = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    moves.add(new Move(i, j));
                }
            }
        }
        return moves;
    }

    //Lav et move (MinMax)
    public void makeMove(Move move, char player) {
        board[move.getRow()][move.getCol()] = player;
    }

    //Backtrack
    public void undoMove(Move move) {
        board[move.getRow()][move.getCol()] = ' ';
    }

    //Er spillet slut?
    public boolean isGameOver() {
        return checkWinner() != ' ' || getAvailableMoves().isEmpty();
    }

    // Check winner hjælpefunktion
    public char checkWinner() {

        // Rækker
        for (int row = 0; row < 3; row++) {
            if (board[row][0] != ' ' &&
                    board[row][0] == board[row][1] &&
                    board[row][1] == board[row][2]) {
                return board[row][0];
            }
        }
        // Kolonner
        for (int col = 0; col < 3; col++) {
            if (board[0][col] != ' ' &&
                    board[0][col] == board[1][col] &&
                    board[1][col] == board[2][col]) {
                return board[0][col];
            }
        }

        // Diagonal \
        if (board[0][0] != ' ' &&
                board[0][0] == board[1][1] &&
                board[1][1] == board[2][2]) {
            return board[0][0];
        }

        // Diagonal /
        if (board[0][2] != ' ' &&
                board[0][2] == board[1][1] &&
                board[1][1] == board[2][0]) {
            return board[0][2];
        }

        return ' '; // ingen vinder
    }

    public int evaluate(char player) {

        int[][] values = {
                {3, 2, 3},
                {2, 4, 2},
                {3, 2, 3}
        };

        char opponent = player == 'X' ? 'O' : 'X';

        char winner = checkWinner();
        if (winner == player) return 1000;
        if (winner == opponent) return -1000;

        int score = 0;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == player)
                    score += values[row][col];
                else if (board[row][col] == opponent)
                    score -= values[row][col];
            }
        }

        return score;
    }

}
