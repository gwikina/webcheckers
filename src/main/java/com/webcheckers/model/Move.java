package com.webcheckers.model;


public class Move {
    //The start Position
    private Position start;
    //The end Position
    private Position end;
    //The Position of the taken Piece
    private Position takenPosition;
    //The Piece taken
    private Piece takenPiece;
    //The Piece moved
    private Piece movedPiece;
    //The valid state given from the ValidateMove
    private ValidateMove.Validation validState;


    public Move(Position start, Position end){
        this.start = start;
        this.end = end;
        this.validState = null;
    }


    public Position getStart() {
        return start;
    }


    public Position getEnd(){
        return end;
    }


    public void setStart(Position start){ this.start = start;}


    public void setTakenPosition(Position taken) {
        this.takenPosition = taken;
    }


    public Position getTakenPosition() {
        return takenPosition;
    }


    public void setTakenPiece(Piece takenPiece) {
        this.takenPiece = takenPiece;
    }


    public Piece getTakenPiece() {
        return takenPiece;
    }

    public void setMovedPiece(Piece movedPiece) {
        this.movedPiece = movedPiece;
    }

    public Piece getMovedPiece() {
        return movedPiece;
    }


    public void setValidState(ValidateMove.Validation validState) {
        this.validState = validState;
    }


    public ValidateMove.Validation getValidState() {
        return validState;
    }
}
