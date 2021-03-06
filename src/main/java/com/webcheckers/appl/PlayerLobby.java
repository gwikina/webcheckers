package com.webcheckers.appl;
import com.webcheckers.model.Player;
import spark.*;

import java.util.*;

public class PlayerLobby {

    private static ArrayList<Player> users;

    private static ArrayList<Player> players;

    private ArrayList<Player> gamePlayers;

    private Player red;

    private Player white;

    private boolean choseInGame;


    public PlayerLobby() {
        users = new ArrayList<>();
        players = new ArrayList<>();
        gamePlayers = new ArrayList<>();
        choseInGame = false;
    }


    public static boolean addUser(Player player){
        if(!users.contains(player) && player != null){
            users.add(player);
            return true;
        }
        return false;
    }


    public static boolean addPlayer(Player player){
        if(!players.contains(player) && player != null){
            players.add(player);
            return true;
        }
        return false;
    }

    public void addGamePlayer(Player player){
        if(!gamePlayers.contains(player) && player != null){
            gamePlayers.add(player);
        }
    }


    public ArrayList<Player> getUsers() {
        return users;
    }


    public ArrayList<Player> getPlayers() {
        return players;
    }


    public ArrayList<Player> getGamePlayers() {
        return gamePlayers;
    }


    public static Player[] playerArray(ArrayList<Player> playerList){
        Player[] playerNames = new Player[playerList.size()];
        for(int i = 0; i < playerList.size(); i++){
            playerNames[i] = playerList.get(i);
        }
        return playerNames;
    }


    public Player getUser(String name){
        Player[] playerArray = this.playerArray(this.users);
        for (Player player : playerArray) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    public boolean isInGame(Player player){ return gamePlayers.contains(player); }

    public void removeUser(Player player){ users.remove(player); }

    public void removePlayer(Player player){ players.remove(player); }

    public void removeGamePlayer(Player player){ gamePlayers.remove(player); }

    public void playerChoseInGame(){
        choseInGame = true;
    }

    public void notChoseInGame(){
        choseInGame = false;
    }

    public boolean isChoseInGame(){
        return choseInGame;
    }
}