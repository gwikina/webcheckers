package com.webcheckers.model;

import java.util.*;

public class Game {
    //The red Player
    private Player redPlayer;
    //The white Player
    private Player whitePlayer;
    //The winner
    private Player winner;
    //The resigned Player
    private Player resignPlayer;
    //The game board
    private Board board;
    //The most recent move
    private Move recentMove;
    //The game's ID
    private int gameID;
    //A map of all the moves made in the game
    private Map<Integer, Move> allMoves;
    //Boolean value for if a turn was made
    private boolean turnMade;

    public Game(Player redPlayer, Player whitePlayer, int gameID){
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.winner = null;
        this.resignPlayer = null;
        this.board = new Board(redPlayer, whitePlayer);
        this.gameID = gameID;
        this.allMoves = new HashMap<>();
        this.turnMade = false;
    }

    public Player getRedPlayer() { return this.redPlayer; }

    public Player getWhitePlayer() {
        return this.whitePlayer;
    }


    public Player setWinner(Player winner){
        this.winner = winner;
        return this.winner;
    }

    public Player getWinner(){ return this.winner; }


    public void setResignPlayer(Player resignPlayer) {
        this.resignPlayer = resignPlayer;
    }

    public Player getResignPlayer() {
        return resignPlayer;
    }


    public void setRecentMove(Move move){
        this.recentMove = move;
    }


    public Move getRecentMove(){
        return this.recentMove;
    }


    public Board getBoard(){
        return this.board;
    }


    public int getGameID(){
        return this.gameID;
    }

    public boolean isTurnMade() {
        return this.turnMade;
    }


    public List<Player> playerList(){
        return new ArrayList<>(Arrays.asList(this.redPlayer, this.whitePlayer));
    }

    public void takePiece(){
        Position takenPosition = this.recentMove.getTakenPosition();
        if(takenPosition != null) {
            Piece piece = board.getPiece(takenPosition.getRow(), takenPosition.getCell());
            board.removePiece(takenPosition.getRow(), takenPosition.getCell());
            board.decrementPieces(piece);
        }
    }


    public boolean isGameOver(){
        return this.board.noPieces();
    }


    public void addMove(Move move){
        allMoves.put(allMoves.size(), move);
    }


    public int getNumMoves(){
        return allMoves.size();
    }


    public Move getMove(int numMove) {
        return allMoves.get(numMove);
    }


    public Player getOpponent(Player player){
        return (player == this.redPlayer) ? this.whitePlayer : this.redPlayer;
    }


    public void doTurn(Move move){
        Piece piece = this.board.getPiece(move.getStart().getRow(), move.getStart().getCell());
        move.setMovedPiece(piece);
        board.updateBoard(move);
        this.addMove(move);
        this.takePiece();// TODO, fix it please
        this.turnMade = true;
        board.validateSpaces();
    }
}