package network;

import Enums.AlertType;
import Enums.Side;
import Requests.*;
import Responses.*;
import Tools.Loop;
import config.Alerts;
import database.ModelLoader;
import game.GameLobby;
import game.battleShip.BattleShipGame;
import model.GameSquad;
import model.Player;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class ClientHandler extends Thread implements RequestHandler {
    private final ResponseSender sender;
    private final GameLobby gameLobby;
    private Side side;
    private BattleShipGame game;
    private Player player;
    private int token;


    public ClientHandler(ResponseSender sender, GameLobby gameLobby) {
        this.token = 0;
        this.sender = sender;
        this.gameLobby = gameLobby;
        Loop loop = new Loop(1, 0, () -> System.out.println(gameLobby.getOnlinePlayers()));
        loop.start();

    }

    public void run() {
        while (true) {
            try {
                Request request = sender.getRequest();
                if (request.getToken() == token) sender.sendResponse(request.takeAct(this));
            } catch (Throwable ignored) {

            }
        }
    }


    @Override
    public Response login(LoginRequest loginRequest) {
        String username = loginRequest.getUserName();
        String password = loginRequest.getPassword();
        if (gameLobby.getOnlinePlayers().contains(username)) {
            return new ShowAlert(Alerts.online(), AlertType.Info);
        }
        if (ModelLoader.isCorrect(username, password)) {
            this.player = ModelLoader.getPlayer(username);
            gameLobby.getOnlinePlayers().add(username);
            SecureRandom random = new SecureRandom();
            this.token = random.nextInt();
            return new SignPlayer(token);
        }
        return new ShowAlert(Alerts.password(), AlertType.Error);
    }

    @Override
    public Response register(RegisterRequest registerRequest) {
        String username = registerRequest.getUserName();
        String password = registerRequest.getPassword();
        if (ModelLoader.isAvailable(username)) {
            ModelLoader.save(username, password, 0, 0, 0);
            this.player = new Player(username, 0, 0, 0);
            gameLobby.getOnlinePlayers().add(username);
            SecureRandom random = new SecureRandom();
            this.token = random.nextInt();
            return new SignPlayer(token);
        }
        return new ShowAlert(Alerts.username(), AlertType.Error);
    }

    @Override
    public Response findGame(FindGameRequest findGameRequest) {
        gameLobby.addToQueue(this);
        return gameStatusResponse();
    }

    @Override
    public Response cellClicked(int i, int j) {
        if (game.getGameState().getTurn() == side) {
            game.clickedCell(i, j, side);
            return gameStatusResponse();
        }
        return new ShowAlert(Alerts.turn(), AlertType.Info);
    }

    @Override
    public Response boardUpdate() {
        return gameStatusResponse();
    }

    @Override
    public Response randomizeBoard() {
        if (game.getGameState().randomized(side)) {
            game.addTime(side);
            game.getBoard(side).randomizeShips();
            game.getBoard(side).putShips();
            game.getBoard(side).setCount();
            return gameStatusResponse();
        }
        return new ShowAlert(Alerts.noAttempt(), AlertType.Error);
    }

    @Override
    public Response readyPlayer() {
        game.getGameState().setPlayerReady(side);
        return new PlayerIsReady();
    }

    @Override
    public Response gameClosed() {
        this.game = null;
        return new SignPlayer(0);
    }

    @Override
    public Response getListOfGames() {
        return new ListOfGamesResponse(getGameSquads());
    }

    @Override
    public Response watchGame(GameSquad gameSquad) {
        BattleShipGame game = gameLobby.getGame(gameSquad);
        if (game == null) return new ShowAlert(Alerts.finish(), AlertType.Info);
        this.game = game;
        return new WatchGameBoardResponse(game.getBoard(Side.PLAYER_ONE), game.getBoard(Side.PLAYER_TWO), game.getPlayer(Side.PLAYER_ONE), game.getPlayer(Side.PLAYER_TWO));
    }

    @Override
    public Response updateWatchGameBoard() {
        if (game == null) {
            return new SignPlayer(0);
        }
        if (game.isInProgress()) {
            return new WatchGameBoardResponse(game.getBoard(Side.PLAYER_ONE), game.getBoard(Side.PLAYER_TWO), game.getPlayer(Side.PLAYER_ONE), game.getPlayer(Side.PLAYER_TWO));
        } else {
            game = null;
            return new SignPlayer(0);
        }
    }

    @Override
    public Response stopWatching() {
        this.game = null;
        return new SignPlayer(0);
    }

    @Override
    public Response updateLeaderBoard() {
        ArrayList<Player> topWin = ModelLoader.getPlayers();
        ArrayList<Player> topLose = ModelLoader.getPlayers();
        ArrayList<Player> topGamesPlayed = ModelLoader.getPlayers();
        ArrayList<Player> topPoints = ModelLoader.getPlayers();
        topWin.sort(Comparator.comparing(Player::getWins));
        topLose.sort(Comparator.comparing(Player::getLosses));
        topGamesPlayed.sort(Comparator.comparing(Player::getGamesPlayed));
        topPoints.sort(Comparator.comparing(Player::getPoint));
        Collections.reverse(topWin);
        Collections.reverse(topLose);
        Collections.reverse(topGamesPlayed);
        Collections.reverse(topPoints);
        gameLobby.loadOnlineStatus(topWin);
        gameLobby.loadOnlineStatus(topLose);
        gameLobby.loadOnlineStatus(topPoints);
        gameLobby.loadOnlineStatus(topGamesPlayed);
        return new LeaderBoardResponse(topWin, topLose, topGamesPlayed, topPoints);
    }

    @Override
    public Response logout() {
        if (player != null) gameLobby.getOnlinePlayers().remove(player.getName());
        return new LogOutResponse();
    }

    @Override
    public Response getPLayer() {
        return new PlayerResponse(player);
    }


    public HashSet<GameSquad> getGameSquads() {
        HashSet<GameSquad> ans = new HashSet<>();
        gameLobby.getGames().forEach(game -> ans.add(new GameSquad(game.getPlayer(Side.PLAYER_ONE).getName()
                , game.getPlayer(Side.PLAYER_TWO).getName(), game.getStats())));
        return ans;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public void setGame(BattleShipGame game) {
        this.game = game;
    }

    public Response gameStatusResponse() {
        if (game == null) {
            return new WaitingBoardResponse();
        }
        game.checkForEndGame();
        checkForConnection();
        if (!game.isPlayerOneReady() || !game.isPlayerTwoReady()) {
            game.startReadyTimers();
            return new EditBoardResponse(game.getBoard(side), game.getTime(side));
        }
        if (game.isInProgress()) {
            if (!gameLobby.getGames().contains(game)) {
                gameLobby.getGames().add(game);
                game.startTurnTimer();
            }
            return new BoardResponse(game.getBoard(side), game.getOpponentBoard(side), game.getTurnText(side), game.getPlayer(side), game.getOpponent(side), game.getTime());
        } else {
            if (game.getWinner() == side) this.player.addWin();
            else this.player.addLoss();
            ModelLoader.save(this.player);
            gameLobby.getGames().remove(game);
            return new EndGameBoard(game.getWinner() == side);
        }
    }


    public void checkForConnection() {
        if (!gameLobby.getOnlinePlayers().contains(game.getOpponent(side).getName())) {
            game.setWinner(side);
            game.readyPlayerOne();
            game.readyPlayerTwo();
        }
    }

    public Player getPlayer() {
        return player;
    }
}
