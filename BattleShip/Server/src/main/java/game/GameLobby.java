package game;

import Enums.Side;
import Enums.Status;
import game.battleShip.BattleShipGame;
import model.GameSquad;
import model.Player;
import network.ClientHandler;

import java.util.ArrayList;
import java.util.HashSet;

public class GameLobby {
    private ClientHandler inWait;
    private final HashSet<BattleShipGame> games = new HashSet<>();
    private final HashSet<String> onlinePlayers = new HashSet<>();

    public void addToQueue(ClientHandler requester) {
        if (inWait == null) {
            inWait = requester;
            inWait.setSide(Side.PLAYER_ONE);
        } else if (!onlinePlayers.contains(inWait.getPlayer().getName())) {
            inWait = requester;
            inWait.setSide(Side.PLAYER_ONE);
        }
        if (inWait != requester) {
            BattleShipGame game = new BattleShipGame();
            requester.setSide(Side.PLAYER_TWO);
            inWait.setGame(game);
            requester.setGame(game);
            game.setPlayerOne(inWait.getPlayer());
            game.setPlayerTwo(requester.getPlayer());
            inWait = null;
        }
    }

    public HashSet<BattleShipGame> getGames() {
        return games;
    }

    public HashSet<String> getOnlinePlayers() {
        return onlinePlayers;
    }

    public BattleShipGame getGame(GameSquad squad) {
        for (BattleShipGame game :
                games) {
            if (game.getPlayer(Side.PLAYER_ONE).getName().equals(squad.getPlayerOne()) && game.getPlayer(Side.PLAYER_TWO).getName().equals(squad.getPlayerTwo()))
                return game;
            if (game.getPlayer(Side.PLAYER_ONE).getName().equals(squad.getPlayerTwo()) && game.getPlayer(Side.PLAYER_TWO).getName().equals(squad.getPlayerOne()))
                return game;
        }
        return null;
    }

    public ArrayList<Player> loadOnlineStatus(ArrayList<Player> players) {
        for (Player player :
                players) {
            if (onlinePlayers.contains(player.getName())) player.setStatus(Status.ONLINE);
            else player.setStatus(Status.OFFLINE);
        }
        return players;
    }
}
