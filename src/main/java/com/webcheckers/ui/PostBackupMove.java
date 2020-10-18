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

public class PostBackupMove implements Route{
    private static final Logger LOG = Logger.getLogger(GetSignInRoute.class.getName());

    private final TemplateEngine templateEngine;

    public PostBackupMove(TemplateEngine templateEngine) {
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