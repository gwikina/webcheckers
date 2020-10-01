package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import java.util.*;

public class GameCenter {
    //Map of all of the games.
    private Map<List<Player>, Game> activeGames;
    //List of all finished games
    private List<Game> gamesOver;
    //Tracker for gameID, number of games.
    private int numOfGames;

    public GameCenter(){
        this.activeGames = new HashMap<>();
        this.gamesOver = new ArrayList<>();
        this.numOfGames = 0;
    }


    public Game makeGame(Player redPlayer, Player whitePlayer){
        this.numOfGames++;
        Game game = new Game(redPlayer, whitePlayer, this.numOfGames);
        activeGames.put(game.playerList(), game);
        return game;
    }


    public void addGameOver(Game game){
        if(activeGames.containsValue(game))
            activeGames.remove(game.playerList());
        if(!gamesOver.contains(game))
            gamesOver.add(game);
    }

    public Game getGame(Player player){
        for(List<Player> players : activeGames.keySet()){
            if(players.contains(player)){
                return activeGames.get(players);
            }
        }
        return null;
    }

    public Game getGame(int gameID){
        for(Game game : gamesOver){
            if(game.getGameID() == gameID){
                return game;
            }
        }
        return null;
    }

    public boolean containsKey(Player player) {
        for (List<Player> players : activeGames.keySet()) {
            if (players.contains(player)) {
                return true;
            }
        }
        return false;
    }


    public List<Game> getGamesOver(){
        return gamesOver;
    }

    public int getNumOfGames(){
        return this.numOfGames;
    }


}
