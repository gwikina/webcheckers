package com.webcheckers.ui.model;

import com.webcheckers.model.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Model-tier")
public class GameTest {
    Player redPlayer = new Player("RED");
    Player whitePlayer = new Player("WHITE");
    int gameID = 12345;
    Game game = new Game(redPlayer, whitePlayer, gameID);

    /**
     * Test the creation of a new Game.
     */
    @Test
    public void testCreateGame() {
        // Analyze results
        assertNotNull(game);
    }

    @Test
    public void testGetRedPlayer() {
        // Analyze results
        assertEquals(redPlayer, game.getRedPlayer());
    }

    @Test
    public void testGetWhitePlayer() {
        // Analyze results
        assertEquals(whitePlayer, game.getWhitePlayer());
    }

    @Test
    public void testSetWinner() {
        game.setWinner(redPlayer);
        // Analyze results
        assertEquals(redPlayer, game.getWinner());
    }

    @Test
    public void testSetResignPlayer() {
        game.setResignPlayer(whitePlayer);
        // Analyze results
        assertEquals(whitePlayer, game.getResignPlayer());
    }

    @Test
    public void testSetRecentMove() {
        Position start = new Position(0, 0);
        Position end = new Position(5, 5);
        Move move = new Move(start, end);

        game.setRecentMove(move);
        // Analyze results
        assertEquals(move, game.getRecentMove());
    }

    @Test
    public void testGetBoard() {
        // Analyze results
        assertNotNull(game.getBoard());
    }

    @Test
    public void testGetID() {
        // Analyze results
        assertEquals(gameID, game.getGameID());
    }

    @Test
    public void testIsTurnMade() {
        // Analyze results
        assertFalse(game.isTurnMade());
    }

    @Test
    public void testPlayerList() {
        ArrayList<Player> expectedPlayer = new ArrayList<Player>(Arrays.asList(this.redPlayer, this.whitePlayer));
        // Analyze results
        assertEquals(game.playerList(), expectedPlayer);
    }

    @Test
    public void testGetNumMoves() {
        assertEquals(game.getNumMoves(), 0);
    }

    @Test
    public void testGetMove() {
        Position start = new Position(0, 0);
        Position end = new Position(1, 1);
        Move move = new Move(start, end);
        game.addMove(move);
        assertEquals(move, game.getMove(0));
    }

    @Test
    public void testGetOpponent() {
        assertEquals(game.getOpponent(redPlayer), whitePlayer);
        assertEquals(game.getOpponent(whitePlayer), redPlayer);
    }

    @Test
    public void testIsGameOver() {
        assertFalse(game.isGameOver());
        game.setResignPlayer(redPlayer);
        assertTrue(game.isGameOver());
    }

    @Test
    public void testGetPiece() {
        assertFalse(game.getBoard().noPieces());
        Position start = new Position(1, 1);
        Position end = new Position(2, 2);
        Move move = new Move(start, end);
        game.setRecentMove(move);
        assertFalse(game.getBoard().noPieces());
    }

    @Test
    public void testDoTurn() {
        Position start = new Position(1, 1);
        Position end = new Position(2, 2);
        Move move = new Move(start, end);
        game.setRecentMove(move);
        game.doTurn(move);
        assertTrue(game.isTurnMade());
    }

    @Test
    public void testTakePiece() {
        Position start = new Position(1, 1);
        Position end = new Position(2, 2);
        Move move = new Move(start, end);
        game.setRecentMove(move);
        assertNull(game.getRecentMove().getTakenPiece());
    }
}
