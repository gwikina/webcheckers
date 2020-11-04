package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.*;
import java.util.logging.Logger;

public class PostSignInRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());

    private static final Message FAILED = Message.info("Sorry, a user with that name already exists, please choose a different name");

    private final TemplateEngine templateEngine;

    private PlayerLobby lobby;

    public PostSignInRoute(TemplateEngine templateEngine, PlayerLobby lobby) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.lobby = lobby;
    }

    @Override
    public Object handle(Request request, Response response) {
        final String name = request.queryParams("currentUser");
        final Map<String, Object> vm = new HashMap<>();

        if (this.lobby.getUser(name)==null) {
            Player newPlayer = new Player(name);
            ArrayList<Player> names = storeCurrentUser(newPlayer, request.session());
            response.redirect(WebServer.HOME_URL);
            return null;
        }
        else{
            vm.put("title", "Welcome!");
            vm.put("message", FAILED);
            return templateEngine.render(new ModelAndView(vm, "signin.ftl"));
        }
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
        return names;
    }

}
