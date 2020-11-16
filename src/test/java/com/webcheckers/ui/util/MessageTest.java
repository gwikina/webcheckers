package com.webcheckers.ui.util;
import static org.junit.jupiter.api.Assertions.*;
import com.webcheckers.model.Board;
import com.webcheckers.model.Player;
import com.webcheckers.model.Position;
import com.webcheckers.util.Message;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.webcheckers.util.Message;

/**
 * The unit test suite for the {@link com.webcheckers.util.Message} component.
 *
 */
@Tag("util-tier")
public class MessageTest {
    Message message = new Message("Test", Message.Type.INFO);
    Message FAILED = Message.error("Sorry, a user with that name already exists, please choose a different name");

    @Test
    public void testGetType() {
        assertEquals(message.getType(), Message.Type.INFO);
    }

    @Test
    public void testGetText() {
        assertEquals(message.getText(), "Test");
    }

    @Test
    public void testError() {
        assertNotNull(FAILED);
    }

    @Test
    public void testIsSuccessful() {
        assertFalse(FAILED.isSuccessful());
        assertTrue(message.isSuccessful());
    }
}
