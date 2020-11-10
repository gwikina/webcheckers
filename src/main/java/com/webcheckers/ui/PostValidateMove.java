package com.webcheckers.ui;

import java.util.*;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.*;
import com.webcheckers.util.Message;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;
import com.google.gson.*;

public class PostValidateMove implements Route{

    private final GameCenter gameCenter;

    //private Jas
    public PostValidateMove(GameCenter gameCenter) {
        this.gameCenter = gameCenter;
    }


    @Override
    public Object handle(Request request, Response response) {
        String move = request.queryParams("actionData");
        Player currentUser= request.session().attribute("currentUser");
        Game game = this.gameCenter.getGame(currentUser);
        Board board = game.getBoard();
        Gson json = new Gson();
        Move M = json.fromJson(move, Move.class);
        new ValidateMove();
        Message message = null;
        if(board.getSpace(M.getStart().getRow(),M.getStart().getCell()).getPiece() == null){
            M.setStart(game.getRecentMove().getStart());
        }
        if (ValidateMove.validateMove(game, M)== ValidateMove.Validation.TOOFAR){
            M.setValidState(ValidateMove.Validation.TOOFAR);
            message = Message.error("Invalid Move: Please Move a Shorter Distance");
        } else if (ValidateMove.validateMove(game, M)== ValidateMove.Validation.OCCUPIED) {
            M.setValidState(ValidateMove.Validation.OCCUPIED);
            message = Message.error("Invalid Move: Please Move to an Open Tile");
        } else if (ValidateMove.validateMove(game, M)== ValidateMove.Validation.JUMPNEEDED) {
            M.setValidState(ValidateMove.Validation.JUMPNEEDED);
            message = Message.error("Invalid Move: Please Jump over the Opponent");
        } else if (ValidateMove.validateMove(game, M)== ValidateMove.Validation.VALIDJUMP) {
            Piece piece = board.getPiece(M.getStart().getRow(), M.getStart().getCell());
            Piece.Type type;

            //Sets the Pieces type to King if it reaches the end of the Board.
            if(M.getEnd().getRow() == 0 || M.getEnd().getRow() == 7) {
                type = Piece.Type.KING;
            }
            else {
                type = piece.getType();
            }

            if(ValidateMove.pieceHasJump(M.getEnd(), game, piece.getColor(), type, false)){
                M.setValidState(ValidateMove.Validation.VALIDJUMP);
                System.out.println(M.getValidState());
                message = Message.info("Press submit and then jump again");
            }

            M.setValidState(ValidateMove.Validation.VALID);
            message = Message.info("This is a valid Jump");
            game.setRecentMove(M);
        }
        else if (ValidateMove.validateMove(game, M)== ValidateMove.Validation.VALID) {
            M.setValidState(ValidateMove.Validation.VALID);
            message = Message.info("Valid Move");
            game.setRecentMove(M);
        }
        game.addMove(M);

        return json.toJson(message);
    }

}
