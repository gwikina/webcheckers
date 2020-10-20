package com.webcheckers.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import com.webcheckers.model.ValidateMove;
import com.webcheckers.util.Message;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;
public class PostResignGame implements Route {
    private static final Logger LOG = Logger.getLogger(GetSignInRoute.class.getName());

    private final TemplateEngine templateEngine;
    private final PlayerLobby lobby;
    private final GameCenter gameCenter;

    public PostResignGame(TemplateEngine templateEngine, GameCenter gameCenter, PlayerLobby lobby) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = gameCenter;
        this.lobby = lobby;
    }

    @Override
    public Object handle(Request request, Response response) {
        final Map<String, Object> vm = new HashMap<>();
        Player currentUser = request.session().attribute("currentUser");
        Game game = this.gameCenter.getGame(currentUser);
        Gson json = new Gson();

        Message message;
        if (game.getBoard().noPieces()) {
            message = Message.error("Cannot resign an completed game!");
        } else {
            game.setResignPlayer(currentUser);
            game.setWinner(game.getOpponent(currentUser));
            message = Message.info("Resigned Successfully");
        }
        //TODO winner page and/or lobby
        //templateEngine.render(new ModelAndView(vm, "lobby.ftl"));
        System.out.println("Winner is: " + game.getWinner().getName());
        System.out.println("Loser is: " + game.getResignPlayer().getName());
        //TODO: Fix error where this does not allow a new game to start
        return json.toJson(message);
    }
}
