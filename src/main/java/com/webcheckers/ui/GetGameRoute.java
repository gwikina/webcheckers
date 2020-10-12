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
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());

    private final TemplateEngine templateEngine;

    private GameCenter gameCenter;

    public GetGameRoute(TemplateEngine templateEngine, GameCenter gameCenter) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = gameCenter;
    }

    @Override
    public Object handle(Request request, Response response) {
        final Map<String, Object> vm = new HashMap<>();
        Player currentUser= request.session().attribute("currentUser");

        Game game = this.gameCenter.getGame(currentUser);
        Player opponent = game.getOpponent(currentUser);

        Board board = game.getBoard();
        BoardView boardView = new BoardView(board, currentUser);

        // Message MSG = Message.info("Please wait for Heather to play");
        vm.put("title", "WebCheckers");
        vm.put("currentUser", currentUser);
        vm.put("gameID", game.getGameID());
        vm.put("viewMode", "PLAY");
        vm.put("redPlayer", currentUser);
        vm.put("whitePlayer", opponent);
        vm.put("activeColor", board.getActiveColor());
        vm.put("board", boardView);
        //vm.put("message", MSG);
        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    }


}
