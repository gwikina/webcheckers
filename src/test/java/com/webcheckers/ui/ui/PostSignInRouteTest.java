package com.webcheckers.ui.ui;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.ui.PostSignInRoute;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.webcheckers.ui.WebServer;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import spark.*;

import com.webcheckers.model.Player;
import com.webcheckers.appl.PlayerLobby;

import spark.HaltException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

import java.lang.reflect.Executable;

/**
 * The unit test suite for {@link PostSignInRoute} component.
 */
@Tag("UI-tier")
public class PostSignInRouteTest {

    private static final String NAME1 = "Sheather";
    private static final String NAME2 = "Barella";

    private static final String PLAYER_LOBBY_EXCEPTION = "This is the exception";

    /**
     * The component-under-test (CuT).
     *
     * <p>
     * This is a stateless component so we only need one.
     * The {@link PlayerLobby} component is not yet thoroughly tested
     * so we can use it safely as a "friendly" dependency.
     */
    private PostSignInRoute CuT;

    // friendly objects
    private Player player1;
    private Player player2;

    // attributes holding mock objects
    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;
    private PlayerLobby playerLobby;

    /**
    * Setup new mock objects for each test.
    */
    @BeforeEach
    public void setup() {
    request = mock(Request.class);
    session = mock(Session.class);
    when(request.session()).thenReturn(session);
    engine = mock(TemplateEngine.class);
    response = mock(Response.class);

    // build the Service and Model objects
    // the PlayerLobby and Player are friendly
    player1 = new Player(NAME1);
    player2 = new Player(NAME2);


    // but mock up and store in session
    playerLobby = mock(PlayerLobby.class);
    when(request.session()).thenReturn(session);

    // create a unique CuT for each test
    CuT = new PostSignInRoute(engine, playerLobby);
    }

    /**
     * Test that the "sign in" action handles invalid players: out of range
     */
    @Test
    public void signin() {

        try{
            // Invoke the test
            CuT.handle(request, response);

            assertTrue(playerLobby.addUser(player1));

            // Analyze the results:
            //   * redirect to the Game view
            verify(response, times(1)).redirect(WebServer.HOME_URL);
        }
        catch(Exception e) {
            // Arrange the test scenario: The session holds a new game.
            when(request.queryParams("name")).thenReturn(NAME1);
            when(session.attribute("player")).thenReturn(player1);
            when(request.session()).thenReturn(session);
            when(request.queryParams("casual")).thenReturn("casual");
            //when(playerLobby.addGamePlayer(player1, Player.getName(NAME1))).thenReturn(player1);
            when(playerLobby.getUser(NAME1)).thenReturn(player1);
        }
    }

    }
