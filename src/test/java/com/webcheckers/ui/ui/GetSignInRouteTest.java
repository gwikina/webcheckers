package com.webcheckers.ui.ui;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.ui.GetSignInRoute;
import com.webcheckers.ui.TemplateEngineTester;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

@Tag("UI-tier")
public class GetSignInRouteTest {

    /**
     * Parameters used for testing.
     */
    private GetSignInRoute CuT;
    private TemplateEngine templateEngine;
    private Request request;
    private Session session;
    private Response response;

    /**
     * Initialization.
     */
    @BeforeEach
    private void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        templateEngine = mock(TemplateEngine.class);
        response = mock(Response.class);
        PlayerLobby playerLobby = mock(PlayerLobby.class);
        CuT = new GetSignInRoute(templateEngine);
    }

    /**
     * See if the attributes from getSignInRoute works.
     */
    @Test
    public void testAttributes(){

        final TemplateEngineTester tester = new TemplateEngineTester();

        when(templateEngine.render(any(ModelAndView.class) )).thenAnswer(tester.makeAnswer());
        CuT.handle(request, response);

        tester.assertViewModelExists();
        tester.assertViewModelIsaMap();

    }
}
