package com.webcheckers.ui.model;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.AI;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.model.Position;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Model-tier")
public class AITest {
    AI ai = new AI();

    /**
     * Test the creation of a new AI.
     */
    @Test
    public void testCreateAPI() {
        // Analyze results
        assertNotNull(ai);
    }

    @Test
    public void testGetPlayer() {
        Player cpu = ai.getPlayer();
        // Analyze results
        assertEquals(cpu.getName(), "CPU");
    }

    @Test
    public void testJumpAverage() {
        final GameCenter CuT = new GameCenter();
        // Invoke test
        // Friendly Player1
        final Player player1 = new Player("Rocky");
        // Friendly Player2
        final Player player2 = new Player("Ella");

        final Game game = CuT.makeGame(player1, player2);

        Position start = new Position(5, 6);

        // Analyze results
        assertEquals(false, ai.jumpAvailable(game, start));
    }

    @Test
    public void testDecideMove() {
        final GameCenter CuT = new GameCenter();
        // Invoke test
        // Friendly Player1
        final Player player1 = new Player("Rocky");
        // Friendly Player2
        final Player player2 = new Player("Ella");

        final Game game = CuT.makeGame(player1, player2);

        // Analyze results
        assertNotNull(ai.decideMove(game));
    }
}
