package org.example.tictactoe.dto;

public class MoveRequest {

    private char[][] board;
    private String player;
    private int depth;
    private String algorithm;


    public char[][] getBoard() { return board; }
    public void setBoard(char[][] board) { this.board = board; }

    public String getPlayer() { return player; }
    public void setPlayer(String player) { this.player = player; }

    public int getDepth() { return depth; }
    public void setDepth(int depth) { this.depth = depth; }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}