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

    public PostSignInRoute(TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    }

    @Override
    public Object handle(Request request, Response response) {
        final String currentUser = request.queryParams("currentUser");
        storeCurrentUser(currentUser, request.session());
        final Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Sign in");
        vm.put("message", SIGNIN_MSG);
        return templateEngine.render(new ModelAndView(vm, "signin.ftl"));
    }

    private void storeCurrentUser(String name, Session session){
        ArrayList<String> names = session.attribute("names");
        if (names == null) {
            names = new ArrayList<>();
            session.attribute("names", names);
        }
        names.add(name);
        Player newPlayer = new Player(name);
        PlayerLobby.addPlayer(newPlayer);
    }
}
