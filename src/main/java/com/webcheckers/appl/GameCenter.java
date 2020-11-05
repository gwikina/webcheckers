/*
 * file: GameCenter.java
 * @description: Functionality for storing data for active games and finished games.
 * @author: Team
 */

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

    //Constructor
    public GameCenter(){
        //HashMap to store active games.
        this.activeGames = new HashMap<>();
        //Array list to store finished games.
        this.gamesOver = new ArrayList<>();
        //Sets initial number of games to zero.
        this.numOfGames = 0;
    }

    /**
     * Function creates a game with red players and white players.
     * @param redPlayer Player that plays red.
     * @param whitePlayer Player that plays white.
     * @return the game created.
     */
    public Game makeGame(Player redPlayer, Player whitePlayer){
        //Increments the number of games played in the GameCenter by 1.
        this.numOfGames++;
        //Creates a new game with red player, white player, and uses the number of games as game id.
        Game game = new Game(redPlayer, whitePlayer, this.numOfGames);
        //Adds ArrayList of Players to activeGames Hashmap as keys, and the game created as the value.
        activeGames.put(game.playerList(), game);
        //returns the game created.
        return game;
    }

    /**
     * Deletes player and game id data for game over.
     * @param game the current game
     */
    public void addGameOver(Game game){
        //if there is a game within active game remove the players from that game.
        if(activeGames.containsValue(game))
            activeGames.remove(game.playerList());
        //If gamesOver does not contain game, add game to ArrayList
        if(!gamesOver.contains(game))
            gamesOver.add(game);
    }

    /**
     * Getter to get the current players within the game.
     * @param player player for current game
     * @return HashMap of active games or null.
     */
    public Game getGame(Player player){
        //For each list of players within the activeGames Hashmap
        for(List<Player> players : activeGames.keySet()){
            //If HashMap key contains a player
            if(players.contains(player)){
                //returns HashMap key for the active game.
                return activeGames.get(players);
            }
        }
        //If there are no players return null.
        return null;
    }

//    /**
//     * Getter to get the current game ID.
//     * @param gameID gameID
//     * @return null or game
//     */
//    public Game getGameOver(Player player){
//        //For each list of players within the activeGames Hashmap
//        for(List<Player> players : gamesOver.keySet()){
//            //If HashMap key contains a player
//            if(players.contains(player)){
//                //returns HashMap key for the active game.
//                return activeGames.get(players);
//            }
//        }
//        //If there are no players return null.
//        return null;
//    }

    /**
     * Determines if there are players in the game.
     * @param player player
     * @return boolean true or false.
     */
    public boolean containsKey(Player player) {
        //For each list of players within the activeGames Hashmap
        for (List<Player> players : activeGames.keySet()) {
            //If the Hashmap key contains a player of type Player.
            if (players.contains(player)) {
                return true;
            }
        }
        return false;
    }

    //Getter to retrieve number of finished games.
    public List<Game> getGamesOver(){
        return gamesOver;
    }

    //Getter to retrieve num of games.
    public int getNumOfGames(){
        return this.numOfGames;
    }


}
