package com.webcheckers.ui.model;

import com.webcheckers.model.Board;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Model-tier")
public class BoardViewTest {
    Player redPlayer = new Player("RED");
    Player whitePlayer = new Player("WHITE");
    Board board = new Board(redPlayer, whitePlayer);
    BoardView boardview1 = new BoardView(board, redPlayer);
    BoardView boardview2 = new BoardView(board, whitePlayer);

    /**
     * Test the creation of a new BoardView.
     */
    @Test
    public void testCreateBoardView() {
        // Analyze results
        assertNotNull(boardview1);
        assertNotNull(boardview2);
    }

    @Test
    public void testIterator() {
        assertNotNull(boardview1.iterator());
        assertNotNull(boardview2.iterator());
    }
}
