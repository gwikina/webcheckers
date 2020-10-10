package com.webcheckers.ui;

import com.webcheckers.*;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Board;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class PostGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());

    private final TemplateEngine templateEngine;

    private PlayerLobby lobby;


    public PostGameRoute(TemplateEngine templateEngine, PlayerLobby lobby) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.lobby = lobby;
    }

    @Override
    public Object handle(Request request, Response response) {
        String name = request.queryParams("opponent");
        lobb
        System.out.println(name);

        final Map<String, Object> vm = new HashMap<>();
        Player currentUser= request.session().attribute("currentUser");

        Player player1= new Player("Heather");
        Board board = new Board(player1, currentUser);
        BoardView boardView = new BoardView(board, currentUser);

       // Message MSG = Message.info("Please wait for Heather to play");
        vm.put("currentUser", currentUser);
        vm.put("title", "WebCheckers");
        vm.put("gameID", "00000000");
        vm.put("viewMode", "PLAY");
        vm.put("redPlayer", player1);
        vm.put("whitePlayer", currentUser);
        vm.put("activeColor", "RED");
        vm.put("board", boardView);
        //vm.put("message", MSG);
        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    }

}
