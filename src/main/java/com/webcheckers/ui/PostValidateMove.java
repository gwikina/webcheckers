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
        Player currentUser = request.session().attribute("currentUser");
        Game game = this.gameCenter.getGame(currentUser);
        Board board = game.getBoard();
        Gson json = new Gson();
        Move M = json.fromJson(move, Move.class);
        new ValidateMove();
        //#essential, becuase double jump makes the turn on a check so it would try to take  the same peices multiple times
        ValidateMove.Validation validation = ValidateMove.validateMove(game, M);
        Message message = null;
        if (board.getSpace(M.getStart().getRow(), M.getStart().getCell()).getPiece() == null) {
            M.setStart(game.getRecentMove().getStart());
        }
        if (validation == ValidateMove.Validation.TOOFAR) {
            M.setValidState(ValidateMove.Validation.TOOFAR);
            message = Message.error("Invalid Move: Please Move a Shorter Distance");
        } else if (validation == ValidateMove.Validation.OCCUPIED) {
            M.setValidState(ValidateMove.Validation.OCCUPIED);
            message = Message.error("Invalid Move: Please Move to an Open Tile");
        } else if (validation == ValidateMove.Validation.JUMPNEEDED) {
            M.setValidState(ValidateMove.Validation.JUMPNEEDED);
            message = Message.error("Invalid Move: Please Jump over the Opponent");
        } else if (validation == ValidateMove.Validation.DOUBLEJUMPNEEDED) {
            M.setValidState(ValidateMove.Validation.DOUBLEJUMPNEEDED);
            message = Message.error("Invalid Move: Please DoubleJump over the Opponent");
        } else if (validation == ValidateMove.Validation.SIDEWAYS) {
            M.setValidState(ValidateMove.Validation.SIDEWAYS);
            message = Message.error("Invalid Move: cannot move sideways");
        } else if (validation == ValidateMove.Validation.VALIDJUMP) {
            message = Message.info("This is a valid Jump");
            game.setRecentMove(M);
        } else if (validation == ValidateMove.Validation.VALID) {
            message = Message.info("Valid Move");
            game.setRecentMove(M);
        }
        System.out.println(ValidateMove.validateMove(game, M));
        game.addMove(M);
        return json.toJson(message);

    }

}
