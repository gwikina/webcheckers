package com.webcheckers.ui.model;

import com.webcheckers.model.*;
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

    @Test
    public void testNoPieces() {
        //Analyze results of initial active color
        assertFalse(board.noPieces());
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (board.getPiece(r, c) != null) {
                    Piece piece = board.getPiece(r, c);
                    board.decrementPieces(piece);
                    board.removePiece(r, c);
                }
            }
        }
        assertTrue(board.noPieces());
    }

    @Test
    public void testUpdateBoard() {
        Position start1 = new Position(0, 0);
        Position end1 = new Position(1, 1);
        Move move1 = new Move(start1, end1);
        board.updateBoard(move1);
        Piece piece1 = board.getPiece(1, 1);
        assertEquals(board.getPiece(1, 1), piece1);
        board.undoMove(move1);
        assertEquals(board.getPiece(0, 0), piece1);
    }

    @Test
    public void testGetRow() {
        Space[][] boardArray = new Space[8][8];
        assertNotNull(board.getRow(0));
        assertNotNull(board.getBackwardsRow(0));
    }
}
