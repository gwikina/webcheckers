package com.webcheckers.model;

import com.webcheckers.ui.WebServer;

public class ValidateMove {

    public enum Validation {

        VALID, TOOFAR, OCCUPIED, JUMPNEEDED, VALIDJUMP

    }

    public static Validation validateMove(Game game, Move move) {

        Board model = game.getBoard();

        final Position startPos = move.getStart();
        final Position endPos = move.getEnd();

        final Space start = model.getSpace(startPos.getRow(), startPos.getCell());
        final Space end = model.getSpace(endPos.getRow(), endPos.getCell());

        final Piece piece = start.getPiece();

        if (!end.isValid())
            return Validation.OCCUPIED;


        int rowChange = Math.abs(startPos.getRow() - endPos.getRow());

        if (rowChange > 2)
            return Validation.TOOFAR;

        //simple move
        if (rowChange == 1) {

            if (teamHasJump(game, piece.getColor()))
                return Validation.JUMPNEEDED;

            return validateSimpleMove(piece.getColor(), piece.getType(), startPos, endPos);

        }

        //jump move
        if (rowChange == 2) {

            return validateJumpMove(game, start, move);
        }


        return Validation.TOOFAR;

    }

    private static Validation validateSimpleMove(Piece.Color color, Piece.Type type, Position start, Position end) {


        int colChange = Math.abs(start.getCell() - end.getCell());
        int rowChange = start.getRow() - end.getRow();

        if (colChange > 1)
            return Validation.TOOFAR;

        if (color.equals(Piece.Color.RED) && type.equals(Piece.Type.SINGLE)) {

            if (rowChange == 1) {
                return Validation.VALID;
            } else
                return Validation.TOOFAR;
        } else if (color.equals(Piece.Color.WHITE) && type.equals(Piece.Type.SINGLE)) {

            if (rowChange == -1)
                return Validation.VALID;
            else
                return Validation.TOOFAR;

        } else if (type.equals(Piece.Type.KING)) {

            if (rowChange == -1 || rowChange == 1)
                return Validation.VALID;
            else
                return Validation.TOOFAR;
        }

        return Validation.TOOFAR;
    }


    private static Validation validateJumpMove(Game game, Space start, Move move) {

        if (checkSimpleJump(move, game, true, start.getPiece().getColor(), start.getPiece().getType())) {

            return Validation.VALIDJUMP;
        } else
            return Validation.TOOFAR;

    }

    private static boolean teamHasJump(Game game, Piece.Color color) {

        Board model = game.getBoard();

        for (int r = 0; r < 8; r++) {

            for (int c = 0; c < 8; c++) {

                if (model.getSpace(r, c).getPiece() != null && model.getSpace(r, c).getPiece().getColor() == color) {
                    Piece.Type type = model.getPiece(r, c).getType();
                    Position position = new Position(r, c);

                    if (pieceHasJump(position, game, color, type, false))
                        return true;
                }

            }
        }
        return false;
    }

    public static boolean pieceHasJump(Position pos, Game game, Piece.Color color, Piece.Type type, boolean realMove) {

        int teamOffset = 1;

        if (color.equals(Piece.Color.RED))
            teamOffset = -1;

        int leftCell = pos.getCell() + 2 * teamOffset;
        int rightCell = pos.getCell() - 2 * teamOffset;

        int forwardRow = pos.getRow() + 2 * teamOffset;
        int backRow = pos.getRow() - 2 * teamOffset;

        if ((forwardRow > 7 || forwardRow < 0) && type != Piece.Type.KING) {
            return false;
        }

        if (leftCell < 8 && leftCell >= 0) {

            Position end = new Position(forwardRow, leftCell);
            Position kingEnd = new Position(backRow, leftCell);
            Move move = new Move(pos, end);
            Move kingMove = new Move(pos, kingEnd);

            if (checkSimpleJump(move, game, realMove, color, type)) {
                return true;
            }
            if (type.equals(Piece.Type.KING) && checkSimpleJump(kingMove, game, realMove, color, type)) {
                return true;
            }
        }
        if (rightCell < 8 && rightCell >= 0) {

            Position end = new Position(forwardRow, rightCell);
            Position kingEnd = new Position(backRow, rightCell);
            Move move = new Move(pos, end);
            Move kingMove = new Move(pos,  kingEnd);

            if (checkSimpleJump(move, game, realMove, color, type)) {
                return true;
            }
            return type.equals(Piece.Type.KING) && checkSimpleJump(kingMove, game, realMove, color, type);
        }

        return false;
    }

    private static boolean checkSimpleJump(Move move, Game game, boolean realMove, Piece.Color color, Piece.Type type) {


        Board model = game.getBoard();

        Position start = move.getStart();
        Position end = move.getEnd();

        if(end.getRow() < 0 || end.getRow() > 7)
            return false;
        if(end.getCell() < 0 || end.getCell() > 7)
            return false;


        int rowdif = start.getRow() - end.getRow();
        int coldif = Math.abs(start.getCell() - end.getCell());


        if (rowdif > 0 && type == Piece.Type.SINGLE && color == Piece.Color.WHITE)
            return false;

        if (rowdif < 0 && type == Piece.Type.SINGLE && color == Piece.Color.RED)
            return false;

        rowdif = Math.abs(rowdif);

        if (coldif == 2 && rowdif == 2) {

            Position taken = new Position((start.getRow() + end.getRow()) / 2, (start.getCell() + end.getCell()) / 2);
            Piece takenPiece = model.getPiece(taken.getRow(), taken.getCell());
            Piece startPiece = model.getPiece(start.getRow(), start.getCell());

            if(model.getPiece(end.getRow(),end.getCell()) != null){
                return false;
            }

            Piece.Color colorUsed;
            if (takenPiece != null) {
                if (startPiece != null) {
                    colorUsed = startPiece.getColor();
                } else {
                    colorUsed = color;
                }
                if (takenPiece.getColor() == colorUsed) {
                    return false;
                } else {
                    if (realMove) {
                        move.setTakenPosition(taken);
                        move.setTakenPiece(takenPiece);
                    }
                    return true;
                }
            }
        }
        return false;
    }

}
