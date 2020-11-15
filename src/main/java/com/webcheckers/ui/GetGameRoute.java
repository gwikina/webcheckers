package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.*;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Board;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class GetGameRoute implements Route {

    private final TemplateEngine templateEngine;

    private GameCenter gameCenter;

    private PlayerLobby lobby;

    public GetGameRoute(TemplateEngine templateEngine, PlayerLobby lobby, GameCenter gameCenter) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = gameCenter;
        this.lobby = lobby;
    }

    @Override
    public Object handle(Request request, Response response) {
        final Map<String, Object> vm = new HashMap<>();
        Player currentUser= request.session().attribute("currentUser");

        if (currentUser!=null && this.gameCenter.getGame(currentUser)!=null) {
            Game game = this.gameCenter.getGame(currentUser);
            Board board = game.getBoard();
            BoardView boardView = new BoardView(board, currentUser);
            if (!game.isGameOver()) {
                vm.put("title", "WebCheckers");
                vm.put("currentUser", currentUser);
                vm.put("gameID", game.getGameID());
                vm.put("viewMode", "PLAY");
                vm.put("redPlayer", game.getRedPlayer());
                vm.put("whitePlayer", game.getWhitePlayer());
                vm.put("activeColor", board.getActiveColor());
                vm.put("board", boardView);
                return templateEngine.render(new ModelAndView(vm, "game.ftl"));
            }
            else{
                Gson json = new Gson();
                final Map<String, Object> modeOptions = new HashMap<>(2);
                modeOptions.put("isGameOver", true);
                this.lobby.removeGamePlayer(currentUser);

                //Player resigned game ending
                if (game.getResignPlayer()!=null){
                   if (currentUser==game.getResignPlayer()) {
                    modeOptions.put("gameOverMessage", "Resigned Successfully, The game is over, Exit when ready");
                    }
                   else {
                    modeOptions.put("gameOverMessage", "Congrats You won by default! Your opponent resigned, Exit when ready");//TODO... all end of game messages
                    }
                    vm.put("modeOptionsAsJSON", json.toJson(modeOptions));
                    vm.put("title", "WebCheckers");
                    vm.put("currentUser", currentUser);
                    vm.put("gameID", game.getGameID());
                    vm.put("viewMode", "PLAY");
                    vm.put("redPlayer", game.getRedPlayer());
                    vm.put("whitePlayer", game.getWhitePlayer());
                    vm.put("activeColor", board.getActiveColor());
                    vm.put("board", boardView);
                    if (!this.lobby.getGamePlayers().contains(game.getOpponent(currentUser))) {
                        this.gameCenter.addGameOver(game);
                    }
                    return templateEngine.render(new ModelAndView(vm, "game.ftl"));
                }
                //Normal game end
                else {
                    if (currentUser==game.getWinner()) {
                        modeOptions.put("gameOverMessage", "Congrats! You won by capturing all pieces! Exit when ready");
                    }
                    else {
                        modeOptions.put("gameOverMessage", "You lost... study, and improve your strategy...");//TODO... all end of game messages
                    }
                    vm.put("modeOptionsAsJSON", json.toJson(modeOptions));
                    vm.put("title", "WebCheckers");
                    vm.put("currentUser", currentUser);
                    vm.put("gameID", game.getGameID());
                    vm.put("viewMode", "PLAY");
                    vm.put("redPlayer", game.getRedPlayer());
                    vm.put("whitePlayer", game.getWhitePlayer());
                    vm.put("activeColor", board.getActiveColor());
                    vm.put("board", boardView);
                    if (!this.lobby.getGamePlayers().contains(game.getOpponent(currentUser))) {
                        this.gameCenter.addGameOver(game);
                    }
                    return templateEngine.render(new ModelAndView(vm, "game.ftl"));
                }
            }
        }
        //this shouldn't happen, but just in case
        else if (this.gameCenter.getGame(currentUser)==null){
            this.lobby.removeGamePlayer(currentUser);
            response.redirect(WebServer.HOME_URL);
            return null;
        }
        else{
            //response.redirect(WebServer.HOME_URL);
            return templateEngine.render(new ModelAndView(vm, "game.ftl")); //404
        }
    }


}
