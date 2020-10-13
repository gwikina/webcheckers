package com.webcheckers.ui.appl;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlayerLobbyTest {
    @Test
    public void testPlayerLobby() {
        final PlayerLobby lobby = new PlayerLobby();
        // Invoke test
        final Player player1 = new Player("Gideon");

        final Player player2 = new Player("Seth");

        lobby.addGamePlayer(player1);
        lobby.addGamePlayer(player2);
        ArrayList<Player> list_of_players = lobby.getGamePlayers();

        ArrayList <Player> expected_players = new ArrayList<Player>();
        expected_players.add(player1);
        expected_players.add(player2);
        // Analyze results
        assertEquals(expected_players, list_of_players);
    }
}
