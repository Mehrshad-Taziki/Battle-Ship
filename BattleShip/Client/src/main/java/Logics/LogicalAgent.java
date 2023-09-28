package Logics;

import Enums.AlertType;
import Graphics.GraphicalAgent;
import Requests.*;
import Responses.Response;
import Responses.ResponseHandler;
import Sender.SocketRequestSender;
import Tools.Loop;
import Tools.TimeTask;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.BattleShipBoard;
import model.GameSquad;
import model.Player;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class LogicalAgent implements ResponseHandler {
    private final GraphicalAgent graphicalAgent;
    private final LinkedList<Request> requests;
    private final Loop loop;
    private final SocketRequestSender requestSender;
    private int gameStatus;
    private int token = 0;
    private Loop gameUpdateLoop;
    private TimeTask timeTask;


    public LogicalAgent(Socket socket, Stage stage) throws IOException {
        this.gameStatus = 0;
        this.requestSender = new SocketRequestSender(socket);
        this.graphicalAgent = new GraphicalAgent(stage, this);
        this.requests = new LinkedList<>();
        this.loop = new Loop(10, 0, this::sendRequests);
        start();
    }

    public void start() {
        graphicalAgent.start();
        loop.start();
    }

    public void addRequest(Request request) {
        synchronized (requests) {
            request.setToken(token);
            requests.add(request);
        }
    }

    public void sendRequests() {
        LinkedList<Request> doneRequests;
        synchronized (requests) {
            doneRequests = new LinkedList<>(requests);
            requests.clear();
        }
        for (Request request :
                doneRequests) {
            Response serverResponse = requestSender.sendRequest(request);
            if (serverResponse != null) serverResponse.takeAct(this);
        }
    }

    @Override
    public void showAlert(String message, AlertType type) {
        Platform.runLater(() -> {
            if (type == AlertType.Error) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initStyle(StageStyle.UTILITY);
                alert.setHeaderText(null);
                alert.setContentText(message);
                alert.showAndWait();
            }
            if (type == AlertType.Info) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setHeaderText(null);
                alert.setContentText(message);
                alert.showAndWait();
            }
            if (type == AlertType.Question) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setHeaderText(null);
                alert.setContentText(message);
                alert.showAndWait();
            }
        });
    }

    @Override
    public void signPlayer(int token) {
        gameStatus = 0;
        if (token != 0) {
            this.token = token;
        }
        if (gameUpdateLoop != null) {
            gameUpdateLoop.stop();
        }
        if (timeTask != null) {
            timeTask.stop();
        }
        graphicalAgent.switchToMainMenu();
    }

    @Override
    public void showStats(Player player) {
        graphicalAgent.switchToStats(player);
    }

    @Override
    public void showWaitingBoard() {
        if (timeTask != null) {
            timeTask.stop();
        }
        if (gameStatus != 1) {
            graphicalAgent.switchToWaitingMenu();
            addRequest(new BoardUpdateRequest());
            gameUpdateLoop = new Loop(2, 0, () -> addRequest(new BoardUpdateRequest()));
            gameUpdateLoop.start();
            gameStatus = 1;
        }
    }

    @Override
    public void showEditBoard(BattleShipBoard clientBoard, int time) {
        if (gameStatus != 2) {
            gameStatus = 2;
            graphicalAgent.switchToEditBoard();
        }
        graphicalAgent.loadViewEditBoard(clientBoard, time);
    }

    @Override
    public void showGameBoard(BattleShipBoard clientBoard, BattleShipBoard opponentBoard, String text, Player clientPlayer, Player opponentPlayer, int time) {
        if (gameStatus != 3) {
            gameStatus = 3;
            graphicalAgent.switchToGameBoard();
        }
        graphicalAgent.loadViewGameBoard(clientBoard, opponentBoard, text, clientPlayer, opponentPlayer, time);
    }


    @Override
    public void showEndGameBoard(boolean wonTheGame) {
        if (gameStatus != 4) {
            gameStatus = 4;
            graphicalAgent.switchToEndGameBoard(wonTheGame);
        }
    }

    @Override
    public void playerIsReady() {
        graphicalAgent.playerReadied();
    }

    @Override
    public void showListOfGames(HashSet<GameSquad> games) {
        graphicalAgent.switchToGamesList(games);
        timeTask = new TimeTask(30, () -> addRequest(new ListOfGamesRequest()));
        timeTask.start();
    }

    @Override
    public void watchGame(BattleShipBoard playerOneBoard, BattleShipBoard playerTwoBoard, Player playerOne, Player playerTwo) {
        if (timeTask != null) {
            timeTask.stop();
        }
        if (gameStatus != 5) {
            gameStatus = 5;
            graphicalAgent.switchToWatchGameBoard();
            addRequest(new WatchBoardUpdateRequest());
            gameUpdateLoop = new Loop(2, 0, () -> addRequest(new WatchBoardUpdateRequest()));
            gameUpdateLoop.start();
        }
        graphicalAgent.loadWatchGameBoard(playerOneBoard, playerTwoBoard, playerOne, playerTwo);
    }

    @Override
    public void updateLeaderBoard(ArrayList<Player> topWin, ArrayList<Player> topLose, ArrayList<Player> topGamesPlayed, ArrayList<Player> topPoint) {
        graphicalAgent.switchToLeaderBoard(topWin, topLose, topGamesPlayed, topPoint);
        timeTask = new TimeTask(15, () -> addRequest(new LeaderBoardRequest()));
        timeTask.start();
    }

    @Override
    public void logOut() {
        Platform.exit();
        System.exit(0);
    }


    public GraphicalAgent getAgent() {
        return graphicalAgent;
    }
}
