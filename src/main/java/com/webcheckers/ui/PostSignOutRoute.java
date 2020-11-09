package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
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
    private final GameCenter gameCenter;
    private final PlayerLobby lobby;

    public PostSignOutRoute(TemplateEngine templateEngine, PlayerLobby lobby, GameCenter gameCenter) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.lobby = lobby;
        this.gameCenter = gameCenter;
    }

    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("PostSignOutRoute is invoked.");

        //Gets the Player from the session.
        Player currentUser= request.session().attribute("currentUser");

        //Gets the PlayerLobby and GameCenter from the WebServer, and the Game
        //from the GameCenter.
        PlayerLobby playerLobby = this.lobby;
        GameCenter gameCenter = this.gameCenter;
        Game game = gameCenter.getGame(currentUser);

        //Sets the resign Player to the player signing out if they were in a Game.
        if(game != null){
            game.setResignPlayer(currentUser);
        }
        System.out.println(currentUser);
        //Removes the Player from the PlayerLobby's User, Player, and GamePlayer lists.
        playerLobby.removeUser(currentUser);
        playerLobby.removePlayer(currentUser);
        playerLobby.removeGamePlayer(currentUser);

        //Invalidates the session and redirects to the Home page.
        request.session().invalidate();
        response.redirect("/");
        return "";

    }

    private ArrayList<Player> removeCurrentUser(Player currentUser, Session session){
        ArrayList<Player> names = session.attribute("names");
        if (names == null) {
            names = this.lobby.getUsers();
        }

        session.attribute("currentUser", null);
        this.lobby.removeUser(currentUser);
        session.attribute("names", names);

        return names;
    }

}
