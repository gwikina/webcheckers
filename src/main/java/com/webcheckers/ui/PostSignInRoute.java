package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.*;
import java.util.logging.Logger;

public class PostSignInRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());

    private static final Message SIGNIN_MSG = Message.info("sign in");

    private final TemplateEngine templateEngine;

    private PlayerLobby lobby;

    public PostSignInRoute(TemplateEngine templateEngine, PlayerLobby lobby) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.lobby = lobby;
    }

    @Override
    public Object handle(Request request, Response response) {
        final String name = request.queryParams("currentUser");
        System.out.println(name);
        Player newPlayer = new Player(name);
        ArrayList<Player> names = storeCurrentUser(newPlayer, request.session());
        final Map<String, Object> vm = new HashMap<>();
        //TODO check name, optionally post Welcome
        vm.put("title", "Welcome!");
        vm.put("currentUser", newPlayer);
        vm.put("names", names);
        System.out.println(newPlayer.getName());
        return templateEngine.render(new ModelAndView(vm, "home.ftl"));
    }

    private ArrayList<Player> storeCurrentUser(Player newPlayer, Session session){
        ArrayList<Player> names = session.attribute("names");
        if (names == null) {
            names = this.lobby.getUsers();
        }
        if (!this.lobby.getUsers().contains(newPlayer)) {
            session.attribute("currentUser", newPlayer);
            this.lobby.addUser(newPlayer);
            session.attribute("names", names);
        }
        System.out.println("lobby=" + this.lobby.getUsers());
        return names;
    }

}
