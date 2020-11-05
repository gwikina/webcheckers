package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.*;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PostCheckSpectate implements Route {
    private final TemplateEngine templateEngine;

    private GameCenter gameCenter;

    public PostCheckSpectate(TemplateEngine templateEngine, GameCenter gameCenter, PlayerLobby lobby) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = gameCenter;
    }

    public Object handle(Request request, Response response) {

        String gameID = request.queryParams("gameID");
        System.out.println("gameId is " + gameID);
        Game game = this.gameCenter.getGame(Integer.getInteger(gameID));
        Player currentUser= request.session().attribute("currentUser");
        Message message;

            Player redPlayer = game.getRedPlayer();
            Player whitePlayer = game.getWhitePlayer();

            if (!game.isGameOver()) {
                message = Message.info("true");
            } else{
                message = Message.info("info:true");
            }


        Gson json = new Gson();
        return json.toJson(message);


    }
}
