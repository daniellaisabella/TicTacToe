package org.example.tictactoe.ai;

import org.example.tictactoe.model.Board;
import org.example.tictactoe.model.Move;

public class AlphaBeta {

    private int nodesVisited;

    public int getNodesVisited() {
        return nodesVisited;
    }

    public Move findBestMove(Board board, int depth, char player) {

        nodesVisited = 0;

        int bestScore = Integer.MIN_VALUE;
        Move bestMove = null;

        for (Move move : board.getAvailableMoves()) {

            board.makeMove(move, player);

            int score = alphabeta(
                    board,
                    depth - 1,
                    Integer.MIN_VALUE,
                    Integer.MAX_VALUE,
                    false,
                    player
            );

            board.undoMove(move);

            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        return bestMove;
    }

    private int alphabeta(Board board,
                          int depth,
                          int alpha,
                          int beta,
                          boolean isMax,
                          char player) {

        nodesVisited++;   // 🔥 TÆL NODE

        if (depth == 0 || board.isGameOver()) {
            return board.evaluate(player);
        }

        char opponent = player == 'X' ? 'O' : 'X';

        if (isMax) {

            int maxEval = Integer.MIN_VALUE;

            for (Move move : board.getAvailableMoves()) {

                board.makeMove(move, player);

                int eval = alphabeta(board, depth - 1, alpha, beta, false, player);

                board.undoMove(move);

                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);

                if (beta <= alpha) break;  // 🔥 PRUNING
            }

            return maxEval;

        } else {

            int minEval = Integer.MAX_VALUE;

            for (Move move : board.getAvailableMoves()) {

                board.makeMove(move, opponent);

                int eval = alphabeta(board, depth - 1, alpha, beta, true, player);

                board.undoMove(move);

                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);

                if (beta <= alpha) break;  // 🔥 PRUNING
            }

            return minEval;
        }
    }
}