package com.webcheckers.model;



public class Piece {
    //The type of the Piece
    private Type type;
    //The color of the Piece
    private Color color;
    //Type of chest piece.
    public enum Type{ SINGLE, KING}
    //Color of chest piece.
    public enum Color{ RED, WHITE}


    public Piece(Type type, Color color){
        this.type = type;
        this.color = color;
    }


    public Type getType(){ return this.type; }


    public Color getColor(){
        return this.color;
    }

    @Override
    public String toString() {
        return "" + color + "";
    }
}
