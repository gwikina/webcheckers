package com.webcheckers.ui.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.webcheckers.model.Player;

/**
 * The unit test suite for the {@link Player} component.
 *
 */
@Tag("Model-tier")
public class PlayerTest {
    Player testPlayer = new Player("Emily");
    Player testPlayer2 = new Player("Emily");

    /**
     * Test the creation of a new Player.
     */
    @Test
    public void testCreatePlayer() {
        // Analyze results
        assertNotNull(testPlayer);
    }

    /**
     * Test the getName function
     */
    @Test
    public void testGetName() {
        assertEquals("Emily", testPlayer.getName());
    }

    /**
     * Test the equals function
     */
    @Test
    public void testEquals() {
        assertTrue(testPlayer.equals(testPlayer2));
    }

}
