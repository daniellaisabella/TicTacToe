package org.example.tictactoe.ai;

import org.example.tictactoe.model.Board;
import org.example.tictactoe.model.Move;

public class Minimax {

    public Move findBestMove(Board board, int depth, char player) {

        int bestScore = Integer.MIN_VALUE;
        Move bestMove = null;

        for (Move move : board.getAvailableMoves()) {

            board.makeMove(move, player);

            int score = minimax(board, depth - 1, false, player);

            board.undoMove(move);

            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        return bestMove;
    }

    private int minimax(Board board, int depth, boolean isMax, char player) {

        if (depth == 0 || board.isGameOver()) {
            return board.evaluate(player);
        }

        char opponent = player == 'X' ? 'O' : 'X';

        if (isMax) {

            int maxEval = Integer.MIN_VALUE;

            for (Move move : board.getAvailableMoves()) {
                board.makeMove(move, player);
                int eval = minimax(board, depth - 1, false, player);
                board.undoMove(move);
                maxEval = Math.max(maxEval, eval);
            }

            return maxEval;

        } else {

            int minEval = Integer.MAX_VALUE;

            for (Move move : board.getAvailableMoves()) {
                board.makeMove(move, opponent);
                int eval = minimax(board, depth - 1, true, player);
                board.undoMove(move);
                minEval = Math.min(minEval, eval);
            }

            return minEval;
        }
    }
}