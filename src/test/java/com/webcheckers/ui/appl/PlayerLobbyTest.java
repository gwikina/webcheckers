package com.webcheckers.ui.appl;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class PlayerLobbyTest {
    @Test
    public void testPlayerLobby() {
        final PlayerLobby lobby = new PlayerLobby();
        // testing adding game players
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
    @Test
    public void testPlayerInGame() {
        //testing player is in game
        final PlayerLobby lobby = new PlayerLobby();
        // testing adding game players
        final Player player1 = new Player("Gideon");

        final Player player2 = new Player("Seth");

        lobby.addGamePlayer(player1);

        // checking players in the game

        assertTrue(lobby.isInGame(player1));
        assertFalse(lobby.isInGame(player2));

    }

    @Test
    public void testRemovePlayer() {
        final PlayerLobby lobby = new PlayerLobby();
        // testing removing game players
        final Player player1 = new Player("Gideon");

        final Player player2 = new Player("Seth");

        lobby.addGamePlayer(player1);
        lobby.addGamePlayer(player2);

        lobby.removeGamePlayer(player1);
        ArrayList<Player> list_of_players = lobby.getGamePlayers();

        ArrayList <Player> expected_players = new ArrayList<Player>();
        expected_players.add(player2);
        // Analyze results
        assertEquals(expected_players, list_of_players);

    }

    @Test
    public void testGetUser() {
        final PlayerLobby lobby = new PlayerLobby();
        // testing removing game players
        final Player player1 = new Player("Gideon");

        final Player player2 = new Player("Seth");

        lobby.addUser(player1);
        lobby.addUser(player2);

        lobby.removeGamePlayer(player1);
        ArrayList<Player> list_of_players = lobby.getGamePlayers();

        ArrayList <Player> expected_players = new ArrayList<Player>();
        expected_players.add(player2);
        // Analyze results

        assertEquals(lobby.getUser("Gideon"), player1);
    }

    @Test
    public void testAddUser() {
        final PlayerLobby lobby = new PlayerLobby();
        // testing removing game players
        final Player player1 = new Player("Gideon");

        final Player player2 = new Player("Seth");

        final Player player3 = null;

        lobby.addUser(player1);

        lobby.removeGamePlayer(player1);
        ArrayList<Player> list_of_players = lobby.getGamePlayers();

        ArrayList <Player> expected_players = new ArrayList<Player>();
        expected_players.add(player2);
        // Analyze results

        assertEquals(true, lobby.addUser(player2));
        assertEquals(false, lobby.addUser(player3));
    }

    @Test
    public void testAddPlayer() {
        final PlayerLobby lobby = new PlayerLobby();
        // testing removing game players
        final Player player1 = new Player("Gideon");

        final Player player2 = new Player("Seth");

        final Player player3 = null;

        lobby.addGamePlayer(player1);

        ArrayList <Player> expected_players = new ArrayList<Player>();
        expected_players.add(player2);

        // Analyze results
        assertEquals(true, lobby.addPlayer(player2));
        assertEquals(false, lobby.addPlayer(player3));
    }

    @Test
    public void testRemoveUser() {
        final PlayerLobby lobby = new PlayerLobby();
        // testing removing game players
        final Player player1 = new Player("Gideon");

        final Player player2 = new Player("Seth");

        lobby.addUser(player1);
        lobby.addUser(player2);

        lobby.removeUser(player1);
        ArrayList<Player> list_of_players = lobby.getUsers();

        ArrayList <Player> expected_players = new ArrayList<Player>();
        expected_players.add(player2);
        // Analyze results
        assertEquals(expected_players, list_of_players);
    }

    @Test
    public void testPlayerChoseInGame() {
        final PlayerLobby lobby = new PlayerLobby();
        // testing removing game players
        final Player player1 = new Player("Gideon");

        final Player player2 = new Player("Seth");

        lobby.addUser(player1);
        lobby.addUser(player2);

        lobby.removeUser(player1);
        ArrayList<Player> list_of_players = lobby.getUsers();

        ArrayList <Player> expected_players = new ArrayList<Player>();
        expected_players.add(player2);
        // Analyze results
        assertEquals(expected_players, list_of_players);
    }

}
