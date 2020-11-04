package com.webcheckers.model;

import java.util.*;


public class AI {
    private Piece.Color AIColor = Piece.Color.WHITE;
    Player cpu;
    Game game;
    Board board;


    public static Move decideMove(Game game) {
        ArrayList<Move> possibleMoves = new ArrayList<>();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                for (int plusRow = -2; plusRow < 3; plusRow++) {
                    for (int plusColumn = -2; plusColumn < 3; plusColumn++) {
                        if (       plusRow != 0
                                && plusColumn != 0
                                && ((plusRow+plusColumn)%2 == 0)
                                && isInBounds(r, c, plusRow, plusColumn)
                                && (game.getBoard().getPiece(r, c) != null)
                                && (game.getBoard().getPiece(r, c).getColor().equals(Piece.Color.WHITE))
                                && (game.getBoard().getPiece(r+plusRow,c+plusColumn) == null)) {
                            Move testMove = new Move(new Position(r, c), new Position(r+plusRow, c+plusColumn));
                            ValidateMove.Validation isValidMove = ValidateMove.validateMove(game, testMove);
                            if (isValidMove == ValidateMove.Validation.VALID
                                || isValidMove == ValidateMove.Validation.VALIDJUMP) {
                                possibleMoves.add(testMove);
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Possible Moves: " + possibleMoves.size());
        Random random = new Random();
        random.setSeed(5);
        if (possibleMoves.size() > 0) { //if no possible moves return null
            int index = random.nextInt(possibleMoves.size());
            return possibleMoves.get(index); //cpu performs a random move from legal moves
        }
        else {
            return null;
        }
    }


    public static boolean jumpAvailable(Game game, Position start) {
        for (int plusRow = -2; plusRow < 3; plusRow+=2) {
            for (int plusColumn = -2; plusColumn < 3; plusColumn+=2) {
                if (plusRow != 0 && plusColumn != 0 && isInBounds(start.getRow(), start.getCell(), plusRow, plusColumn)) {
                    Move testMove = new Move(start, new Position(start.getRow()+plusRow, start.getCell()+plusColumn));
                    ValidateMove.Validation isValidMultiJump = ValidateMove.validateMove(game, testMove);
                    if (isValidMultiJump == ValidateMove.Validation.VALID
                        || isValidMultiJump == ValidateMove.Validation.VALIDJUMP) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    private static boolean isInBounds(int r, int c, int plusRow, int plusColumn) {
        return (r+plusRow<8 && c+plusColumn<8 && r+plusRow>=0 && c+plusColumn>=0);
    }


    public Player getPlayer() {
        return cpu;
    }


    public AI() {
        cpu = new Player("CPU");
    }
}
