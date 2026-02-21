package org.example.tictactoe.controller;

import org.example.tictactoe.dto.MoveRequest;
import org.example.tictactoe.model.Board;
import org.example.tictactoe.model.Move;
import org.example.tictactoe.service.GameService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/move")
    public Move getBestMove(@RequestBody MoveRequest request) {

        Board board = new Board(request.getBoard());

        return gameService.getBestMove(
                board,
                request.getDepth(),
                request.getPlayer().charAt(0),
                request.getAlgorithm()
        );
    }
}