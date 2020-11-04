package com.webcheckers.ui.model;

import com.webcheckers.model.Board;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Model-tier")
public class BoardViewTest {
    Player redPlayer = new Player("RED");
    Player whitePlayer = new Player("WHITE");
    Board board = new Board(redPlayer, whitePlayer);

    /**
     * Test the creation of a new BoardView.
     */
    @Test
    public void testCreateBoardView() {
        // Analyze results
        assertNotNull(board);
    }

}
