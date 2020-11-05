package com.webcheckers.ui.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.ui.PostSignOutRoute;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("UI-tier")
public class PostSignOutRouteTest {

    /**
     * Parameters to be used in testing.
     */
    private PostSignOutRoute CuT;
    private TemplateEngine templateEngine;
    private Request request;
    private Session session;
    private Response response;
    private PlayerLobby lobby;

    /**
     * The setup of the PostSignOutRoute.
     */
    @BeforeEach
    private void setup() {
        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        templateEngine = mock(TemplateEngine.class);
        CuT = new PostSignOutRoute(templateEngine, lobby);
    }

    /**
     * Tests the PostSignOut.
     */
    @Test
    public void test_PostSignOut(){
        assertEquals(CuT.handle(request, response), "");
    }
}
