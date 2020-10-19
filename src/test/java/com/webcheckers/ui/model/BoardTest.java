package com.webcheckers.ui.model;

import com.webcheckers.model.Board;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Model-tier")
public class BoardTest {
    Player testPlayer = new Player("Emily");
    Player testPlayer2 = new Player("Heather");
    Board board = new Board(testPlayer, testPlayer2);

    /**
     * Test the creation of a new Board.
     */
    @Test
    public void testCreateBoard() {
        // Analyze results
        assertNotNull(board);
    }

    @Test
    public void testGetWhitePlayer() {
        // Analyze results
        assertEquals(testPlayer, board.getRedPlayer());
    }

    @Test
    public void testGetRedPlayer() {
        // Analyze results
        assertEquals(testPlayer2, board.getWhitePlayer());
    }

    @Test
    public void testIsSpaceValid() {
        // Analyze results
        assertFalse(board.isSpaceValid(1, 1));
        assertFalse(board.isSpaceValid(7, 7));
        assertFalse(board.isSpaceValid(5, 6));
    }


}
