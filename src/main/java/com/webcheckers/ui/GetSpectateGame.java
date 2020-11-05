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
    private final PlayerLobby lobby;

    private GameCenter gameCenter;


    public GetSpectateGame(TemplateEngine templateEngine, GameCenter gameCenter, PlayerLobby lobby) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = gameCenter;
        this.lobby = lobby;
    }

    @Override
    public Object handle(Request request, Response response) {
        final Map<String, Object> vm = new HashMap<>();
        Player currentUser = request.session().attribute("currentUser");
        String spectatedPLayer = request.queryParams("spectatedPlayer");

        String gameID = request.queryParams("gameID");
        System.out.println("gameId is " + gameID);
        Game game;
        Board board;
        BoardView boardView;

        if (gameID!=null) {
            game = this.gameCenter.getGame(Integer.getInteger(gameID));
        }else{
            game = this.gameCenter.getGame(this.lobby.getUser(spectatedPLayer));
        }

        board = game.getBoard();
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
