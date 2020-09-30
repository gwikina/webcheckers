package com.webcheckers.ui;

import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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
        final String name = request.queryParams("name");
        storeCurrentUser(name, request.session());
        final Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Sign in");
        vm.put("message", SIGNIN_MSG);
        return templateEngine.render(new ModelAndView(vm, "signin.ftl"));
    }

    private void storeCurrentUser(String name, Session session){
        name = session.attribute("name");
        if (name==null) {
            name = new String();
        }
        session.attribute("CurrentUser", name);
    }
}
