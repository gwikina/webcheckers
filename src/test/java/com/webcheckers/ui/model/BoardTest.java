package com.webcheckers.ui.model;

import com.webcheckers.model.Board;
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



}
