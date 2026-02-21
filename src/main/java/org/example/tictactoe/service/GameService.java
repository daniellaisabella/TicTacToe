package org.example.tictactoe.service;

import org.example.tictactoe.ai.Minimax;
import org.example.tictactoe.ai.AlphaBeta;
import org.example.tictactoe.dto.MoveResponse;
import org.example.tictactoe.model.Board;
import org.example.tictactoe.model.Move;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    public MoveResponse getBestMove(Board board,
                                    int depth,
                                    char player,
                                    String algorithm) {

        if ("alphabeta".equalsIgnoreCase(algorithm)) {

            AlphaBeta alphaBeta = new AlphaBeta();
            Move move = alphaBeta.findBestMove(board, depth, player);

            return new MoveResponse(
                    move.getRow(),
                    move.getCol(),
                    alphaBeta.getNodesVisited()
            );
        }

        Minimax minimax = new Minimax();
        Move move = minimax.findBestMove(board, depth, player);

        return new MoveResponse(
                move.getRow(),
                move.getCol(),
                minimax.getNodesVisited()
        );
    }
}