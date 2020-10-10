package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.logging.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class PostSignOutRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());
    public static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");


    private final TemplateEngine templateEngine;

    private PlayerLobby lobby;
    public PostSignOutRoute(TemplateEngine templateEngine, PlayerLobby lobby) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.lobby = lobby;
    }

    @Override
    public Object handle(Request request, Response response) {
        final String name = request.queryParams("currentUser");
        System.out.println(name);
        final Map<String, Object> vm = new HashMap<>();
        Player currentUser = request.session().attribute("currentUser");
        ArrayList<Player> names = removeCurrentUser(currentUser, request.session());
        vm.put("title", "Welcome!");
        vm.put("currentUser", null);
        vm.put("names", names);
        vm.put("message", WELCOME_MSG);
        System.out.println("removing " + currentUser.getName() + " from lobby");
        return templateEngine.render(new ModelAndView(vm, "home.ftl"));
    }

    private ArrayList<Player> removeCurrentUser(Player currentUser, Session session){
        ArrayList<Player> names = session.attribute("names");
        if (names == null) {
            names = this.lobby.getUsers();
        }
        session.attribute("currentUser", null);
        this.lobby.removeUser(currentUser);
        session.attribute("names", names);

        System.out.println("lobby=" + this.lobby.getUsers());
        return names;
    }

}
