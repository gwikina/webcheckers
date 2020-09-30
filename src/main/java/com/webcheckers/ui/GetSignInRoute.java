package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.util.Message;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;
import spark.TemplateEngine;
import static spark.Spark.halt;


/**
 * The {@code GET /game} route handler.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 * @author <a href='mailto:jrv@se.rit.edu'>Jim Vallino</a>
 */
public class GetSignInRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private static final Message SIGNIN_MSG = Message.info("sign in");

    private final TemplateEngine templateEngine;

    public GetSignInRoute(TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    }

    @Override
    public Object handle(Request request, Response response) {
        final boolean currentUser = true;
        storeCurrentUser(currentUser, request.session());
        final Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Welcome!");
        vm.put("message", SIGNIN_MSG);
        return templateEngine.render(new ModelAndView(vm, "home.ftl"));
    }

    private void storeCurrentUser(boolean currentUser, Session session){
        session.attribute("currentUser", currentUser);
    }
}