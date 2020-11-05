package com.webcheckers.ui.model;

import com.webcheckers.model.Move;
import com.webcheckers.model.Position;
import com.webcheckers.model.Row;
import com.webcheckers.model.Space;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tag("Model-tier")
public class RowTest {
    Space[] spaceArray = new Space[0];
    Row row = new Row(1, spaceArray);

    /**
     * Test the creation of a new Move.
     */
    @Test
    public void testCreateRow() {
        // Analyze results
        assertNotNull(row);
    }

    @Test
    public void testGetIndex() {
        // Analyze results
        assertEquals(1, row.getIndex());
    }

    @Test
    public void testIterator() {
        assertNotNull(row.iterator());
    }
}
