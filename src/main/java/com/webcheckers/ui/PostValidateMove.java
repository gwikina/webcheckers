package com.webcheckers.ui;

import java.util.*;
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
import com.google.gson.*;

public class PostValidateMove implements Route{
    private static final Logger LOG = Logger.getLogger(GetSignInRoute.class.getName());

    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;

    //private Jas
    public PostValidateMove(TemplateEngine templateEngine, GameCenter gameCenter) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = gameCenter;
    }

    @Override
    public Object handle(Request request, Response response) {
        String move = request.queryParams("actionData");
        String gameID = request.queryParams("gameID");
        System.out.println(move);

        Gson json = new Gson();
        Move M = json.fromJson(move, Move.class);
        ValidateMove evaluator = new ValidateMove();
        Game game = this.gameCenter.getGame(Integer.getInteger(gameID));
       // evaluator.validateMove(game, M);


        final Map<String, Object> vm = new HashMap<>();
//        vm.put("title", "Sign in");
//        vm.put("message", SIGNIN_MSG);
        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    }

}
