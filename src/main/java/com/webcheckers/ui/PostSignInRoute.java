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
        storeCurrentUser(newPlayer, request.session());
        final Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Sign in");
        vm.put("message", SIGNIN_MSG);
        vm.put("currentUser", newPlayer);
        System.out.println(newPlayer.getName());
        return templateEngine.render(new ModelAndView(vm, "home.ftl"));
    }

    private void storeCurrentUser(Player newPlayer, Session session){
        session.attribute("currentUser", newPlayer);
        this.lobby.addGamePlayer(newPlayer);
        this.lobby.addUser(newPlayer);
        ArrayList<String> names = session.attribute("names");
        if (names == null) {
            names = new ArrayList<String>();
            session.attribute("names", names);
            names.add(newPlayer.name);
        }
        session.attribute("names", names);
        System.out.println(names);
    }

}
