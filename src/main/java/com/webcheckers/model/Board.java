package com.webcheckers.model;


import java.util.ArrayList;
import java.util.List;


public class Board {

    //The dimension of the board
    private static final int BOARD_SIZE = 8;
    //The matrix of Spaces on the board
    private Space[][] boardArray;
    //The white Player
    private Player whitePlayer;
    //The red Player
    private Player redPlayer;
    //The active color on the board
    private Piece.Color activeColor;
    //Number of white pieces on the board.
    private int whitePieces;
    //Number of red pieces on the board.
    private int redPieces;



    public Board(Player redPlayer, Player whitePlayer){

        boardArray = new Space[BOARD_SIZE][BOARD_SIZE];
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.activeColor = Piece.Color.RED;
        this.whitePieces = 12;
        this.redPieces = 12;
        initializeSpaces();
        this.validateSpaces();

    }

    private void initializeSpaces(){

        for(int r = 0; r< BOARD_SIZE; r++){
            for (int c = 0; c< BOARD_SIZE; c++){
                if((c+r)%2 ==0){

                    boardArray[r][c] = new Space(c, false);
                }
                else if( (c+r)%2 != 0){
                    boardArray[r][c] = new Space(c, true);
                    if(r<3) {
                        addPiece(r, c, new Piece(Piece.Type.SINGLE, Piece.Color.WHITE));
                    }
                    else if(r>4)
                        addPiece(r,c,new Piece(Piece.Type.SINGLE, Piece.Color.RED));

                }
            }
        }
    }

    public void validateSpaces(){
        for(int r = 0; r< BOARD_SIZE; r++) {
            for (int c = 0; c < BOARD_SIZE; c++) {
                if((c+r)%2 == 0){
                    this.getSpace(r, c).setViable(false);
                }
                if(this.getPiece(r, c) != null){
                    this.getSpace(r, c).setViable(false);
                }
                if(((c+r)%2 != 0) && this.getPiece(r, c) == null){
                    this.getSpace(r, c).setViable(true);
                }
            }
        }
    }

    public void allValidSpaces() {
        for (int r = 0; r < BOARD_SIZE; r++) {
            for (int c = 0; c < BOARD_SIZE; c++) {
                this.getSpace(r, c).setViable(true);
            }
        }
    }

    public Space[] getRow(int row){ return boardArray[row];}

    public Space[] getBackwardsRow(int row){

        Space[] temp = this.getRow(row);
        Space[] backwardsRow = new Space[BOARD_SIZE];

        for(int i = 0; i<BOARD_SIZE; i++){

            backwardsRow[(BOARD_SIZE-i)-1] = temp[i];

        }
        return backwardsRow;
    }

    /**
     *Get the white player
     * @return the white player
     */
    public Player getWhitePlayer(){return whitePlayer; }

    /**
     *Get the red player
     * @return the red player
     */
    public Player getRedPlayer(){return redPlayer;}

    /**
     * Checks if space is a valid space for a piece to exist
     *
     * @param row (index)
     * @param col (index)
     * @return true if valid(black space) false if not(white space)2w
     */
    public boolean isSpaceValid(int row, int col ){

        return boardArray[row][col].isValid() && boardArray[row][col].getPiece() == null;
    }

    /**
     *Adds a piece to a location on the board
     * @param row The row the piece is added to
     * @param col The column the piece is added to
     * @param piece The piece added
     */
    public void addPiece(int row, int col, Piece piece){

        if(boardArray[row][col].getPiece() == null && isSpaceValid(row, col)){

            boardArray[row][col].addPiece(piece);

        }

    }

    /**
     * Gets the space at a row and col
     * @param row The row of the space
     * @param col The column of the space
     * @return The space
     */
    public Space getSpace(int row, int col){

        return boardArray[row][col];
    }

    /**
     *Removes a piece from a specific space
     * @param row The row of the removed piece
     * @param col The column of the removed piece
     */
    public void removePiece(int row, int col){
        if(boardArray[row][col] != null){
            boardArray[row][col].addPiece(null);
        }
    }

    /**
     * Gets the piece at a certain row and column
     * @param row the row the piece is at
     * @param col the column the piece is at
     * @return the piece at the specified row and column
     */
    public Piece getPiece(int row, int col){
        Space space = this.getSpace(row, col);
        return space.getPiece();
    }

    /**
     * Changes thee active color
     */
    public void changeActiveColor(){
        this.activeColor = (this.activeColor == Piece.Color.RED) ? Piece.Color.WHITE :Piece.Color.RED;
    }

    /**
     * Gets the active color
     * @return the active color
     */
    public Piece.Color getActiveColor(){
        return this.activeColor;
    }

    /**
     * Checks if one of the Players has no Pieces left
     * @return if a Player has no Pieces
     */
    public boolean noPieces() {
        return (this.whitePieces == 0 || this.redPieces == 0);
    }

    /**
     * Decrements the amount of pieces of the given Piece's color
     * @param piece the given Piece
     */
    public void decrementPieces(Piece piece){
        Piece.Color color = piece.getColor();
        if(color == Piece.Color.RED){
            this.redPieces--;
        }
        else{
            this.whitePieces--;
        }
    }

    /**
     * Updates the game board when a move has been made. This takes account
     * of the new move changes the piece to a king piece if it reaches the
     * end of the board
     * @param move The move made
     */
    public void updateBoard(Move move){
        Position start = move.getStart();
        Position end = move.getEnd();
        Piece piece = this.getPiece(start.getRow(), start.getCell());
        this.removePiece(start.getRow(), start.getCell());
        if((end.getRow() == 0 && piece.getColor() == Piece.Color.RED)
                || (end.getRow() == 7 && piece.getColor() == Piece.Color.WHITE)) {
            Piece temp = new Piece(Piece.Type.KING, piece.getColor());
            this.addPiece(end.getRow(), end.getCell(), temp);
        }
        this.addPiece(end.getRow(), end.getCell(), piece);
    }

    /**
     * Reverses a given moves start and end. It removes the Piece at the end
     * Position, and adds the moved Piece to the start Position.
     * @param move The given Move to undo.
     */
    public void undoMove(Move move){
        Position start = move.getEnd();
        Position end = move.getStart();
        Piece piece = move.getMovedPiece();
        this.removePiece(start.getRow(), start.getCell());
        this.addPiece(end.getRow(), end.getCell(), piece);
    }
}
