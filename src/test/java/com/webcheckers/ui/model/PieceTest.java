package com.webcheckers.ui.model;

import com.webcheckers.model.Move;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Position;
import com.webcheckers.model.ValidateMove;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tag("Model-tier")
public class PieceTest {
    Piece piece1 = new Piece(Piece.Type.SINGLE, Piece.Color.WHITE);
    Piece piece2 = new Piece(Piece.Type.KING, Piece.Color.RED);

    /**
     * Test the creation of a new Piece.
     */
    @Test
    public void testCreatePiece() {
        // Analyze results
        assertNotNull(piece1);
        assertNotNull(piece2);
    }

    @Test
    public void testGetType() {
        // Analyze results
        assertEquals(Piece.Type.SINGLE, piece1.getType());
        assertEquals(Piece.Type.KING, piece2.getType());
    }

    @Test
    public void testGetColor() {
        // Analyze results
        assertEquals(Piece.Color.WHITE, piece1.getColor());
        assertEquals(Piece.Color.RED, piece2.getColor());
    }

    @Test
    public void testToString() {
        // Analyze results
        String string1 = "WHITE";
        String string2 = "RED";
        assertEquals(string1, piece1.toString());
        assertEquals(string2, piece2.toString());
    }

}
