package com.webcheckers.ui.model;

import com.webcheckers.model.Board;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import com.webcheckers.model.Space;
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

    @Test
    public void testActiveColor() {
        //Analyze results of initial active color
        assertEquals(Piece.Color.RED, board.getActiveColor());
        //Analyze results after changing active color
        board.changeActiveColor();
        assertEquals(Piece.Color.WHITE, board.getActiveColor());
        board.changeActiveColor();
        assertEquals(Piece.Color.RED, board.getActiveColor());
    }

    @Test
    public void testAllValidSpaces() {
        //Analyze results of initial active color
        board.allValidSpaces();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Space space = board.getSpace(r, c);
                assertTrue(space.isValid());
            }
        }
    }


}
