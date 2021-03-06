package com.webcheckers.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import com.webcheckers.util.Message;


public class GetHomeRoute implements Route {
  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

  public static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");

  private final TemplateEngine templateEngine;

  private PlayerLobby lobby;

  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetHomeRoute(final TemplateEngine templateEngine, PlayerLobby lobby) {
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    LOG.config("GetHomeRoute is initialized.");
    this.lobby=lobby;
  }

  /**
   * Render the WebCheckers Home page.
   *
   * @param request
   *   the HTTP request
   * @param response
   *   the HTTP response
   *
   * @return
   *   the rendered HTML for the Home page
   */
  @Override
  public Object handle(Request request, Response response) {
    LOG.finer("GetHomeRoute is invoked.");

    Map<String, Object> vm = new HashMap<>();
    vm.put("title", "Welcome!");

    // display a user message in the Home page
    vm.put("message", WELCOME_MSG);
    Player currentUser = request.session().attribute("currentUser");
    ArrayList<Player> names = request.session().attribute("names");
    if (currentUser != null){
      if (this.lobby.isInGame(currentUser)){
        response.redirect(WebServer.GAME_URL);
      }
      PlayerLobby playerLobby = storeLobby(this.lobby, request.session());
      vm.remove("message");
      vm.put("currentUser", currentUser);
      vm.put("names", names);
      vm.put("lobby", playerLobby);

    }

    // render the View
    return templateEngine.render(new ModelAndView(vm , "home.ftl"));
  }

  private PlayerLobby storeLobby(PlayerLobby LOBBY, Session session){
    PlayerLobby playerLobby  = session.attribute("lobby");
    if (playerLobby == null) {
      playerLobby = LOBBY; //TODO might not update, if so --> remove if
    }
      session.attribute("lobby", playerLobby);

    return playerLobby;
  }
}
