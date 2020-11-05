package com.webcheckers.ui.ui;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.ui.PostSignInRoute;
import com.webcheckers.ui.WebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


/**
 * The unit test suite for {@link PostSignInRoute} component.
 */
@Tag("UI-tier")
public class PostSignInRouteTest {

    private static final String NAME1 = "Gideon";
    private static final String NAME2 = "Heather";

    /**
     * The component-under-test (CuT).
     *
     * <p>
     * This is a stateless component so we only need one.
     * The {@link PlayerLobby} component is not yet thoroughly tested
     * so we can use it safely as a "friendly" dependency.
     */
    private PostSignInRoute CuT;
    private Player player1;

    private Request request;
    private Session session;
    private Response response;
    private PlayerLobby playerLobby;

    /**
    * Setup new mock objects for each test.
    */
    @BeforeEach
    public void setup() {
    request = mock(Request.class);
    session = mock(Session.class);
    when(request.session()).thenReturn(session);
    TemplateEngine engine = mock(TemplateEngine.class);
    response = mock(Response.class);

    player1 = new Player(NAME1);
    Player player2 = new Player(NAME2);


    playerLobby = mock(PlayerLobby.class);
    when(request.session()).thenReturn(session);

    CuT = new PostSignInRoute(engine, playerLobby);
    }

    /**
     * Test that the "sign in" action handles invalid players: out of range
     */
    @Test
    public void signin() {

        try{
            CuT.handle(request, response);

            assertTrue(PlayerLobby.addUser(player1));
            verify(response, times(1)).redirect(WebServer.HOME_URL);
        }
        catch(Exception e) {
            when(request.queryParams("name")).thenReturn(NAME1);
            when(session.attribute("player")).thenReturn(player1);
            when(request.session()).thenReturn(session);
            when(request.queryParams("casual")).thenReturn("casual");
            when(playerLobby.getUser(NAME1)).thenReturn(player1);
            }
        }

    }
