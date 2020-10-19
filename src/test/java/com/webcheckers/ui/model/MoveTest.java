package com.webcheckers.ui.model;

import com.webcheckers.model.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tag("Model-tier")
public class MoveTest {
    Position position1 = new Position(5, 5);
    Position position2 = new Position(7, 8);
    Move move = new Move(position1, position2);

    /**
     * Test the creation of a new Move.
     */
    @Test
    public void testCreateMove() {
        // Analyze results
        assertNotNull(move);
    }

    @Test
    public void testGetStart() {
        // Analyze results
        assertEquals(position1, move.getStart());
    }

    @Test
    public void testGetEnd() {
        // Analyze results
        assertEquals(position2, move.getEnd());
    }

    @Test
    public void testSetStart() {
        Position newStart = new Position(1, 1);
        move.setStart(newStart);
        // Analyze results
        assertEquals(newStart, move.getStart());
    }

    @Test
    public void testSetPositionTaken() {
        Position taken = new Position(1, 1);
        move.setTakenPosition(taken);
        // Analyze results
        assertEquals(taken, move.getTakenPosition());
    }

    @Test
    public void testSetTakenPiece() {
        Piece piece1 = new Piece(Piece.Type.SINGLE, Piece.Color.RED);
        move.setTakenPiece(piece1);
        // Analyze results
        assertEquals(piece1, move.getTakenPiece());
    }

    @Test
    public void testSetMovedPiece() {
        Piece piece1 = new Piece(Piece.Type.SINGLE, Piece.Color.RED);
        move.setMovedPiece(piece1);
        // Analyze results
        assertEquals(piece1, move.getMovedPiece());
    }

    @Test
    public void testSetValidState() {
        ValidateMove.Validation validState = null;
        move.setValidState(validState);
        // Analyze results
        assertEquals(validState, move.getValidState());
    }

}
