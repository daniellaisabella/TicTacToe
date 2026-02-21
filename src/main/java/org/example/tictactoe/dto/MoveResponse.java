package org.example.tictactoe.dto;

public class MoveResponse {

    private int row;
    private int col;
    private int nodesVisited;

    public MoveResponse(int row, int col, int nodesVisited) {
        this.row = row;
        this.col = col;
        this.nodesVisited = nodesVisited;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public int getNodesVisited() { return nodesVisited; }
}