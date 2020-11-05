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
    private final PlayerLobby lobby;

    private GameCenter gameCenter;

    public PostCheckSpectate(TemplateEngine templateEngine, GameCenter gameCenter, PlayerLobby lobby) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = gameCenter;
        this.lobby = lobby;
    }

    public Object handle(Request request, Response response) {
        Game game;

        game = this.gameCenter.getGame(request.session().attribute("spectatedPLayer"));

        Message message;

        System.out.println(game);
        if (game != null) {
            message = Message.info("true");
        } else {
            message = Message.info("The game has ended please Exit");
        }

        Gson json = new Gson();
        return json.toJson(message);


    }
}
