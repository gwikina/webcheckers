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
        final Map<String, Object> vm = new HashMap<>();

        String move = request.queryParams("actionData");
        Player currentUser= request.session().attribute("currentUser");
        System.out.println(move);

        Gson json = new Gson();
        Move M = json.fromJson(move, Move.class);
        ValidateMove evaluator = new ValidateMove();
        Game game = this.gameCenter.getGame(currentUser);;
        System.out.println(evaluator.validateMove(game, M));
        Message message;
        if (evaluator.validateMove(game, M)!= ValidateMove.Validation.VALID){
            message = Message.error("nope");
        }else{
            message = Message.info("yeah");
        }
        game.addMove(M);

        return json.toJson(message);
    }

}
