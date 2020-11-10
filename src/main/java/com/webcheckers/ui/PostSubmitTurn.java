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
import spark.*;

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
        Player currentUser= request.session().attribute("currentUser");
        Game game = this.gameCenter.getGame(currentUser);;

        Gson json = new Gson();
        Move M = game.getMove(game.getNumMoves()-1);
        ValidateMove evaluator = new ValidateMove();

        Message message = null;


        //TODO IMPORTANT : We have the last move (even if unmade), and new move coordinates via game.setRecentMove(M);
        //TODO: if we move the piece after one valid jump, the returned move only contains the new start and end position
        if (evaluator.validateMove(game, M) == ValidateMove.Validation.VALID) {
            message = Message.info("move completed");
            game.setRecentMove(M);
            game.doTurn(M);
            game.getBoard().changeActiveColor();
        }else if (M.getValidState() == ValidateMove.Validation.VALIDJUMP) {
            game.doTurn(M);
            Spark.post(WebServer.VALIDATE_MOVE, new PostValidateMove(gameCenter));
        }
        else{
            message = Message.error(evaluator.validateMove(game, M).toString());
        }
        return json.toJson(message);
    }
}
