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
public class PostSubmitTurn implements Route{
    private static final Logger LOG = Logger.getLogger(GetSignInRoute.class.getName());

    private final TemplateEngine templateEngine;
    private GameCenter gameCenter;

    public PostSubmitTurn(TemplateEngine templateEngine, GameCenter gameCenter) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = gameCenter;
    }

    @Override
    public Object handle(Request request, Response response) {
        String move = request.queryParams("actionData");
        Player currentUser= request.session().attribute("currentUser");
        Game game = this.gameCenter.getGame(currentUser);;

        Gson json = new Gson();
        Move M = game.getMove(game.getNumMoves()-1);
        ValidateMove evaluator = new ValidateMove();
        System.out.println("game = " + game + " move = " + M);


        System.out.println(evaluator.validateMove(game, M));
        Message message;
        if (evaluator.validateMove(game, M) == ValidateMove.Validation.VALID || evaluator.validateMove(game, M) == ValidateMove.Validation.VALIDJUMP){
            message = Message.info("yeah");
            game.setRecentMove(M);
            game.doTurn(M);
            game.getBoard().changeActiveColor();
        }else{
            message = Message.error(evaluator.validateMove(game, M).toString());
            //response.redirect(WebServer.GAME_URL);
        }

        return json.toJson(message);
    }
}
