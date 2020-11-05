package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.*;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GetSpectateGame implements Route {
    private final TemplateEngine templateEngine;

    private GameCenter gameCenter;


    public GetSpectateGame(TemplateEngine templateEngine, GameCenter gameCenter) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = gameCenter;
    }

    @Override
    public Object handle(Request request, Response response) {
        final Map<String, Object> vm = new HashMap<>();
        Player currentUser = request.session().attribute("currentUser");
        String gameID = request.queryParams("gameID");
        System.out.println("gameId is " + gameID);
        Game game = this.gameCenter.getGame(Integer.getInteger(gameID));
        Board board = game.getBoard();
        BoardView boardView;
        if (board.getActiveColor() == Piece.Color.RED) {
            boardView = new BoardView(board, game.getRedPlayer());
        }else{
            boardView = new BoardView(board, game.getWhitePlayer());
        }
        if (!game.isGameOver()) {
                vm.put("currentUser", currentUser);

                vm.put("title", "WebCheckers");
                vm.put("gameID", game.getGameID());
                vm.put("viewMode", "SPECTATOR");
                vm.put("redPlayer", game.getRedPlayer());
                vm.put("whitePlayer", game.getWhitePlayer());
                vm.put("activeColor", board.getActiveColor());
                vm.put("board", boardView);
                return templateEngine.render(new ModelAndView(vm, "game.ftl"));
            } else {
                final Map<String, Object> modeOptions = new HashMap<>(2);
                modeOptions.put("isGameOver", true);
                vm.put("currentUser", currentUser);

                vm.put("title", "WebCheckers");
                vm.put("gameID", game.getGameID());
                vm.put("viewMode", "SPECTATOR");
                vm.put("redPlayer", game.getRedPlayer());
                vm.put("whitePlayer", game.getWhitePlayer());
                vm.put("activeColor", board.getActiveColor());
                vm.put("board", boardView);
                return templateEngine.render(new ModelAndView(vm, "game.ftl"));
        }
    }
}
