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

public class PostBackupMove implements Route{
    private static final Logger LOG = Logger.getLogger(GetSignInRoute.class.getName());

    private final TemplateEngine templateEngine;

    private GameCenter gameCenter;

    public PostBackupMove(TemplateEngine templateEngine, GameCenter gameCenter) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = gameCenter;
    }

    @Override
    public Object handle(Request request, Response response) {
        Player currentUser= request.session().attribute("currentUser");
        Game game = this.gameCenter.getGame(currentUser);;
        Move lastMove = game.getRecentMove();

        Gson json = new Gson();
        Message message;

        if (lastMove!=null) {
            message = Message.info("Success! Backed up");
            //game.getBoard().undoMove(lastMove);
        }
        else{
            message = Message.info("Success! Backed up");
            //game.getBoard().undoMove(game.getMove(0));
        }


        return json.toJson(message);
    }
}
