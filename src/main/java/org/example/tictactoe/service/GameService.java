package org.example.tictactoe.service;

import org.example.tictactoe.ai.Minimax;
import org.example.tictactoe.model.Board;
import org.example.tictactoe.model.Move;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    public Move getBestMove(Board board, int depth, char player, String algorithm) {

        if ("alphabeta".equalsIgnoreCase(algorithm)) {
            // senere: return new AlphaBeta().findBestMove(...)
            Minimax minimax = new Minimax();
            return minimax.findBestMove(board, depth, player);
        }

        Minimax minimax = new Minimax();
        return minimax.findBestMove(board, depth, player);
    }
}