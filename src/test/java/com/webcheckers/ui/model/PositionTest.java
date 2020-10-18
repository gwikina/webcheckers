package com.webcheckers.ui.model;

import com.webcheckers.model.Player;
import com.webcheckers.model.Position;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Model-tier")
public class PositionTest {
    Position position1 = new Position(5, 8);

    /**
     * Test the creation of a new Position.
     */
    @Test
    public void testCreatePosition() {
        // Analyze results
        assertNotNull(position1);
    }

    /**
     * Test the getName function
     */
    @Test
    public void testGetRow() {
        assertEquals(5, position1.getRow());
    }

    /**
     * Test the equals function
     */
    @Test
    public void testGetCell() {
        assertEquals(8, position1.getCell());
    }
}
