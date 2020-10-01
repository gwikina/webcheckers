package com.webcheckers.model;

public class Position {
    //The row index
    private int row;
    //The cell index
    private int cell;


    public Position(int row, int cell){
        this.row = row;
        this.cell = cell;
    }


    public int getRow() {
        return row;
    }

    public int getCell() {
        return cell;
    }
}
