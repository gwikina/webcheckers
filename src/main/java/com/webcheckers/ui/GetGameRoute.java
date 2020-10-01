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

public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());

    private static final Message SIGNIN_MSG = Message.info("sign in");

    private final TemplateEngine templateEngine;

    private PlayerLobby lobby;

    public GetGameRoute(TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    }

    @Override
    public Object handle(Request request, Response response) {
        final Map<String, Object> vm = new HashMap<>();

        Player player1= new Player("Heather");
        Player currentUser= new Player("John");
        Board board = new Board(player1, currentUser);
        BoardView boardView = new BoardView(board, currentUser);

        Message MSG = Message.info("Please wait for Heather to play");
        vm.put("currentUser", currentUser);
        vm.put("title", "WebCheckers");
        vm.put("gameID", "00000000");
        vm.put("viewMode", "PLAY");
        vm.put("redPlayer", player1);
        vm.put("whitePlayer", currentUser);
        vm.put("activeColor", "RED");
        vm.put("board", boardView);
        vm.put("message", MSG);
        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    }

    private void storeCurrentUser(String name, Session session){
        Player newPlayer = new Player(name);
        this.lobby.addGamePlayer(newPlayer);
        session.attribute("currentUser", newPlayer);
        this.lobby.addUser(newPlayer);
        ArrayList<Player> names = session.attribute("names");
        if (names == null) {
            names = new ArrayList<Player>();
            session.attribute("names", names);
        }
        session.attribute("names", this.lobby.getGamePlayers());
    }




}
