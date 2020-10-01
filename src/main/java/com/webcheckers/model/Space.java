package com.webcheckers.model;

import com.webcheckers.model.Piece;


public class Space {
    //The column(Index) of the Space
    private int cellIdx;
    //The piece on the space
    private Piece piece;
    //The viability of the space
    private boolean isViable;

    public Space(int index, boolean isViable){

        this.cellIdx = index;
        this.isViable = isViable;
    }

    public int getCellIdx() {
        return cellIdx;
    }


    public boolean isValid(){
        return isViable;
    }

    public void setViable(boolean viable) {
        isViable = viable;
    }

    public Piece getPiece(){
        return piece;
    }

    public void addPiece(Piece piece){
            this.piece = piece;
    }
}
