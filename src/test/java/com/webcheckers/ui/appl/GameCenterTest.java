package com.webcheckers.ui.appl;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import static org.mockito.Mockito.mock;

import com.webcheckers.model.Player;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.webcheckers.model.Game;

/**
 * The unit test suite for the {@link GameCenter} component.
 *
 */
@Tag("Application-tier")
public class GameCenterTest {

    /**
     * Test the ability to make a new Game.
     */
    @Test
    public void testMakeGame() {
        final GameCenter CuT = new GameCenter();
        // Invoke test
        // Friendly Player1
        final Player player1 = new Player("Rocky");
        // Friendly Player2
        final Player player2 = new Player("Ella");

        final Game game = CuT.makeGame(player1, player2);
        // Analyze results
        assertNotNull(game);
    }

}
