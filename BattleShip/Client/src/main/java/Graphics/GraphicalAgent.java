package Graphics;

import Graphics.Views.EndGameBoardView;
import Graphics.Views.StatsView;
import Logics.Controllers.*;
import Logics.LogicalAgent;
import Requests.LogOutRequest;
import config.FrameStats;
import config.PageAddresses;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.BattleShipBoard;
import model.GameSquad;
import model.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class GraphicalAgent {
    private final LogicalAgent logicalAgent;
    private final Stage mainStage;
    private EditBoardController editBoardController;
    private GameBoardController gameBoardController;
    private WatchGameController watchGameController;
    private LeaderBoardController leaderBoardController;


    public GraphicalAgent(Stage stage, LogicalAgent logicalAgent) {
        this.logicalAgent = logicalAgent;
        this.mainStage = stage;
    }

    public void start() {
        try {
            mainStage.setTitle(FrameStats.title());
            mainStage.setMaxHeight(FrameStats.height());
            mainStage.setMaxWidth(FrameStats.width());
            mainStage.setMinHeight(FrameStats.height());
            mainStage.setMinWidth(FrameStats.width());
            mainStage.setResizable(FrameStats.resizable());
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PageAddresses.welcome()));
            Parent root = fxmlLoader.load();
            new WelcomePageController(fxmlLoader.getController(), logicalAgent);
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.setOnCloseRequest(t -> logicalAgent.addRequest(new LogOutRequest()));
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToRegisterForm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(PageAddresses.register()));
            Parent root = fxmlLoader.load();
            new RegisterPageController(fxmlLoader.getController(), logicalAgent);
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToLoginForm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(PageAddresses.login()));
            Parent root = fxmlLoader.load();
            new LoginPageController(fxmlLoader.getController(), logicalAgent);
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToMainMenu() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource(PageAddresses.main()));
                    Parent root = fxmlLoader.load();
                    new MainMenuController(fxmlLoader.getController(), logicalAgent);
                    Scene scene = new Scene(root);
                    mainStage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void switchToWaitingMenu() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource(PageAddresses.waiting()));
                    Parent root = fxmlLoader.load();
                    Scene scene = new Scene(root);
                    mainStage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void switchToEditBoard() {
        Platform.runLater(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource(PageAddresses.edit()));
                            Parent root = fxmlLoader.load();
                            editBoardController = new EditBoardController(fxmlLoader.getController(), logicalAgent);
                            Scene scene = new Scene(root);
                            mainStage.setScene(scene);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    public void switchToGameBoard() {
        Platform.runLater(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource(PageAddresses.game()));
                            Parent root = fxmlLoader.load();
                            gameBoardController = new GameBoardController(fxmlLoader.getController(), logicalAgent);
                            Scene scene = new Scene(root);
                            mainStage.setScene(scene);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    public void switchToEndGameBoard(boolean wonTheGame) {
        Platform.runLater(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource(PageAddresses.end()));
                            Parent root = fxmlLoader.load();
                            ((EndGameBoardView) fxmlLoader.getController()).loadWinLabel(wonTheGame);
                            new EndGameController(fxmlLoader.getController(), logicalAgent);
                            Scene scene = new Scene(root);
                            mainStage.setScene(scene);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    public void switchToGamesList(HashSet<GameSquad> games) {
        Platform.runLater(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource(PageAddresses.gameList()));
                            Parent root = fxmlLoader.load();
                            fxmlLoader.getController();
                            new ListOfGamesController(fxmlLoader.getController(), logicalAgent).loadGamesList(games);
                            Scene scene = new Scene(root);
                            mainStage.setScene(scene);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    public void switchToWatchGameBoard() {
        Platform.runLater(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource(PageAddresses.watch()));
                            Parent root = fxmlLoader.load();
                            watchGameController = new WatchGameController(fxmlLoader.getController(), logicalAgent);
                            Scene scene = new Scene(root);
                            mainStage.setScene(scene);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    public void switchToLeaderBoard(ArrayList<Player> topWin, ArrayList<Player> topLose, ArrayList<Player> topGamesPlayed, ArrayList<Player> topPoint) {
        Platform.runLater(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource(PageAddresses.leaderBoard()));
                            Parent root = fxmlLoader.load();
                            leaderBoardController = new LeaderBoardController(fxmlLoader.getController(), logicalAgent);
                            leaderBoardController.loadLeaderBoard(topWin, topLose, topGamesPlayed, topPoint);
                            Scene scene = new Scene(root);
                            mainStage.setScene(scene);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    public void switchToStats(Player player) {
        Platform.runLater(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource(PageAddresses.stat()));
                            Parent root = fxmlLoader.load();
                            ((StatsView) fxmlLoader.getController()).load(player);
                            ((StatsView) fxmlLoader.getController()).loadBackListener(() -> logicalAgent.signPlayer(0));
                            Scene scene = new Scene(root);
                            mainStage.setScene(scene);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    public void playerReadied() {
        editBoardController.playerIsReady();
    }

    public void loadViewEditBoard(BattleShipBoard clientBoard, int time) {
        if (editBoardController != null) {
            editBoardController.loadViewBoard(clientBoard, time);
        }
    }

    public void loadViewGameBoard(BattleShipBoard clientBoard, BattleShipBoard enemyBoard, String turnText, Player clientPlayer, Player opponentPlayer, int time) {
        if (gameBoardController != null) {
            gameBoardController.loadBoard(clientBoard, enemyBoard, turnText, clientPlayer, opponentPlayer, time);
        }
    }


    public void loadWatchGameBoard(BattleShipBoard clientBoard, BattleShipBoard enemyBoard, Player clientPlayer, Player opponentPlayer) {
        if (watchGameController != null) {
            watchGameController.buildBoard(clientBoard, enemyBoard, clientPlayer, opponentPlayer);
        }
    }


}
