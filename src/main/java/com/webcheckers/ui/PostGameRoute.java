package com.webcheckers.ui;

import com.webcheckers.*;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Board;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.get;

public class PostGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());

    private final TemplateEngine templateEngine;

    private PlayerLobby lobby;
    private GameCenter gameCenter;


    public PostGameRoute(TemplateEngine templateEngine, PlayerLobby lobby, GameCenter gameCenter) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.lobby = lobby;
        this.gameCenter = gameCenter;
    }

    @Override
    public Object handle(Request request, Response response) {
        final Map<String, Object> vm = new HashMap<>();
        Player currentUser= request.session().attribute("currentUser");

        String opponentName = request.queryParams("opponent");
        Player opponent = lobby.getUser(opponentName);
        System.out.println(opponentName);
        if (!this.lobby.isInGame(opponent)) {
            Game game = this.gameCenter.makeGame(currentUser, opponent);

            this.lobby.addGamePlayer(currentUser);
            this.lobby.addGamePlayer(opponent);


            Board board = game.getBoard();
            BoardView boardView = new BoardView(board, currentUser);

            vm.put("title", "WebCheckers");
            vm.put("currentUser", currentUser);
            vm.put("gameID", game.getGameID());
            vm.put("viewMode", "PLAY");
            vm.put("redPlayer", currentUser);
            vm.put("whitePlayer", opponent);
            vm.put("activeColor", board.getActiveColor());
            vm.put("board", boardView);
            return templateEngine.render(new ModelAndView(vm, "game.ftl"));
        }   else{
            response.redirect(WebServer.HOME_URL);
            return templateEngine.render(new ModelAndView(vm, "game.ftl"));
        }
    }

}
