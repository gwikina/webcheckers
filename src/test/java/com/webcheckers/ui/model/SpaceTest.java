package com.webcheckers.ui.model;

import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import com.webcheckers.model.Space;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Model-tier")
public class SpaceTest {

    Space space1 = new Space(15, true);
    Space space2 = new Space(3, false);

    /**
     * Test the creation of a new Space.
     */
    @Test
    public void testCreateSpace() {
        // Analyze results
        assertNotNull(space1);
    }

    /**
     * Test the getCellIdx function
     */
    @Test
    public void testGetCellIdx() {
        assertEquals(15, space1.getCellIdx());
    }

    /**
     * Test the isValid function
     */
    @Test
    public void testIsValid() {
        assertEquals(false, space2.isValid());
    }

    /**
     * Test the setViable function
     */
    @Test
    public void testSetViable() {
        space2.setViable(true);
        assertEquals(true, space2.isValid());
    }

    /**
     * Test the addPiece function
     */
    @Test
    public void testAddPiece() {
        Piece piece1 = new Piece(Piece.Type.SINGLE, Piece.Color.RED);
        space2.addPiece(piece1);
        assertEquals(piece1, space2.getPiece());
    }

}
