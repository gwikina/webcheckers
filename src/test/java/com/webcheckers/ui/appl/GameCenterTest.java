package com.webcheckers.ui.appl;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import static org.mockito.Mockito.mock;

import com.webcheckers.model.Player;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.webcheckers.model.Game;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Test the ability to get a Game.
     */
    @Test
    public void testGetGame() {
        final GameCenter CuT = new GameCenter();
        // Invoke test
        // Friendly Player1
        final Player player1 = new Player("Rocky");
        // Friendly Player2
        final Player player2 = new Player("Ella");

        final Game game = CuT.makeGame(player1, player2);
        // Analyze results
        assertNotNull(CuT.getGame(player1));
    }

    /**
     * Test the ability to end a game.
     */
    @Test
    public void testAddGameOver() {
        final GameCenter CuT = new GameCenter();
        // Invoke test
        // Friendly Player1
        final Player player1 = new Player("Rocky");
        // Friendly Player2
        final Player player2 = new Player("Ella");

        final Game game = CuT.makeGame(player1, player2);
        // Analyze results

        assertNotNull(CuT.getGame(player1));

        CuT.addGameOver(game);
        assertNull(CuT.getGame(player1));
    }

    /**
     * Test the containsKey method
     */
    @Test
    public void testContainsKey() {
        final GameCenter CuT = new GameCenter();
        // Invoke test
        // Friendly Player1
        final Player player1 = new Player("Rocky");
        // Friendly Player2
        final Player player2 = new Player("Ella");
        final Player player3 = new Player("TEST");

        final Game game = CuT.makeGame(player1, player2);
        // Analyze results


        assertEquals(CuT.containsKey(player2), true);
        assertEquals(CuT.containsKey(player3), false);
    }

    /**
     * Test the containsKey method
     */
    @Test
    public void testGetGameInt() {
        final GameCenter CuT = new GameCenter();
        // Invoke test
        // Friendly Player1
        final Player player1 = new Player("Rocky");
        // Friendly Player2
        final Player player2 = new Player("Ella");

        final Game game = CuT.makeGame(player1, player2);
        int gameID = game.getGameID();
        CuT.addGameOver(game);

        // Analyze results
        assertEquals(CuT.getGame(gameID), game);
        assertEquals(CuT.getGame(00000), null);
    }

    /**
     * Test the containsKey method
     */
    @Test
    public void testGetGamesOver() {
        final GameCenter CuT = new GameCenter();
        // Invoke test
        // Friendly Player1
        final Player player1 = new Player("Rocky");
        // Friendly Player2
        final Player player2 = new Player("Ella");

        final Game game = CuT.makeGame(player1, player2);
        CuT.addGameOver(game);
        List<Game> gameOver = new ArrayList<>();
        gameOver.add(game);

        // Analyze results
        assertEquals(CuT.getGamesOver(), gameOver);
    }

    /**
     * Test the containsKey method
     */
    @Test
    public void testGetNumOfGames() {
        final GameCenter CuT = new GameCenter();
        // Invoke test
        // Friendly Player1
        final Player player1 = new Player("Rocky");
        // Friendly Player2
        final Player player2 = new Player("Ella");

        final Game game = CuT.makeGame(player1, player2);
        int numGames = 1;

        // Analyze results
        assertEquals(CuT.getNumOfGames(), numGames);
    }

}
