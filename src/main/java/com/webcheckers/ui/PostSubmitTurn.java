package com.webcheckers.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;
public class PostSubmitTurn implements Route{
    private static final Logger LOG = Logger.getLogger(GetSignInRoute.class.getName());

    private static final Message SIGNIN_MSG = Message.info("Please sign in");

    private final TemplateEngine templateEngine;

    public PostSubmitTurn(TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    }

    @Override
    public Object handle(Request request, Response response) {
        final Map<String, Object> vm = new HashMap<>();
//        vm.put("title", "Sign in");
//        vm.put("message", SIGNIN_MSG);
        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    }
}
