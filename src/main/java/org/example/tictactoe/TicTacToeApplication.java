package org.example.tictactoe;
import org.example.tictactoe.model.Board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicTacToeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicTacToeApplication.class, args);

        char[][] test = {
                {'X','O',' '},
                {' ','X',' '},
                {'O',' ',' '}
        };

        Board board = new Board(test);

        System.out.println(board.getAvailableMoves().size());
    }


}
