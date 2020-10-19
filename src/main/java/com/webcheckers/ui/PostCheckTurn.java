package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.*;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class PostCheckTurn implements Route{
    private static final Logger LOG = Logger.getLogger(GetSignInRoute.class.getName());

    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;

    //private Jas
    public PostCheckTurn(TemplateEngine templateEngine, GameCenter gameCenter) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = gameCenter;
    }

    @Override
    public Object handle(Request request, Response response) {
        Player currentUser= request.session().attribute("currentUser");

        Game game = gameCenter.getGame(currentUser);
        Player redPlayer = game.getRedPlayer();
        Player whitePlayer = game.getWhitePlayer();

        Message message;

        if (currentUser == redPlayer && game.getBoard().getActiveColor() == Piece.Color.RED ) {
            message = Message.info("true");
        }else if (currentUser == whitePlayer && game.getBoard().getActiveColor() == Piece.Color.WHITE ){
            message = Message.info("true");
        }else {
            message = Message.info("false");
        }

        Gson json = new Gson();
        return json.toJson(message);
    }
}
