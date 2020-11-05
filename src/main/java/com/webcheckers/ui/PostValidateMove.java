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

        Gson json = new Gson();
        Move M = json.fromJson(move, Move.class);
        ValidateMove evaluator = new ValidateMove();
        Game game = this.gameCenter.getGame(currentUser);;
        Message message = null;
        if (evaluator.validateMove(game, M)== ValidateMove.Validation.TOOFAR){
            message = Message.error("Invalid Move: Please Move a Shorter Distance");
        } else if (evaluator.validateMove(game, M)== ValidateMove.Validation.OCCUPIED) {
            message = Message.error("Invalid Move: Please Move to an Open Tile");
        } else if (evaluator.validateMove(game, M)== ValidateMove.Validation.JUMPNEEDED) {
            message = Message.error("Invalid Move: Please Jump over the Opponent");
        } else if (evaluator.validateMove(game, M)== ValidateMove.Validation.VALIDJUMP) {
            message = Message.info("This is a valid Jump");
        } else if (evaluator.validateMove(game, M)== ValidateMove.Validation.VALID) {
            message = Message.info("Valid Move");
            game.setRecentMove(M);
        }
        game.addMove(M);

        return json.toJson(message);
    }

}
