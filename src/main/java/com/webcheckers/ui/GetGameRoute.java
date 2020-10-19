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

public class GetGameRoute implements Route {

    private final TemplateEngine templateEngine;

    private GameCenter gameCenter;

    private PlayerLobby lobby;

    public GetGameRoute(TemplateEngine templateEngine, PlayerLobby lobby, GameCenter gameCenter) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = gameCenter;
        this.lobby = lobby;
    }

    @Override
    public Object handle(Request request, Response response) {
        final Map<String, Object> vm = new HashMap<>();
        Player currentUser= request.session().attribute("currentUser");

        if (currentUser!=null) {
            Game game = this.gameCenter.getGame(currentUser);
            Board board = game.getBoard();
            BoardView boardView = new BoardView(board, currentUser);

            vm.put("title", "WebCheckers");
            vm.put("currentUser", currentUser);
            vm.put("gameID", game.getGameID());
            vm.put("viewMode", "PLAY");
            vm.put("redPlayer", game.getRedPlayer());
            vm.put("whitePlayer", game.getWhitePlayer());
            vm.put("activeColor", board.getActiveColor());
            vm.put("board", boardView);
            return templateEngine.render(new ModelAndView(vm, "game.ftl"));
        }
        else{
            response.redirect(WebServer.HOME_URL);
            return templateEngine.render(new ModelAndView(vm, "game.ftl"));
        }
    }


}
