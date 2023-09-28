package Graphics.Views;

import Listener.Listener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Player;

import java.io.IOException;
import java.util.ArrayList;

public class LeaderBoardView {
    private Listener backListener;

    @FXML
    private GridPane topPointPane;

    @FXML
    private  GridPane topWinsPane;

    @FXML
    private  GridPane topLosesPane;

    @FXML
    private   GridPane mostGamesPane;

    public void load(ArrayList<Player> topWin, ArrayList<Player> topLose, ArrayList<Player> topGamesPlayed, ArrayList<Player> topPoint) {
        Platform.runLater(() -> {
            topPointPane.getChildren().clear();
            topWinsPane.getChildren().clear();
            topLosesPane.getChildren().clear();
            mostGamesPane.getChildren().clear();
        });

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    for (Player player :
                            topWin) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/Scenes/WelcomePage/Components/LeaderBoardComponent.fxml"));
                        AnchorPane anchorPane = fxmlLoader.load();
                        ((LeaderBoardComponent) fxmlLoader.getController()).loadNameLabel(player.getName(), player.getWins(), player.getStatus());
                        topWinsPane.add(anchorPane, 0, topWinsPane.getRowCount() + 1);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    for (Player player :
                            topLose) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/Scenes/WelcomePage/Components/LeaderBoardComponent.fxml"));
                        AnchorPane anchorPane = fxmlLoader.load();
                        ((LeaderBoardComponent) fxmlLoader.getController()).loadNameLabel(player.getName(), player.getLosses(), player.getStatus());
                        topLosesPane.add(anchorPane, 0, topLosesPane.getRowCount() + 1);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    for (Player player :
                            topGamesPlayed) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/Scenes/WelcomePage/Components/LeaderBoardComponent.fxml"));
                        AnchorPane anchorPane = fxmlLoader.load();
                        ((LeaderBoardComponent) fxmlLoader.getController()).loadNameLabel(player.getName(), player.getGamesPlayed(), player.getStatus());
                        mostGamesPane.add(anchorPane, 0, mostGamesPane.getRowCount() + 1);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    for (Player player :
                            topPoint) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/Scenes/WelcomePage/Components/LeaderBoardComponent.fxml"));
                        AnchorPane anchorPane = fxmlLoader.load();
                        ((LeaderBoardComponent) fxmlLoader.getController()).loadNameLabel(player.getName(), player.getPoint(), player.getStatus());
                        topPointPane.add(anchorPane, 0, topPointPane.getRowCount() + 1);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void loadBackListener(Listener listener){
        this.backListener = listener;
    }
    public void back() {
        backListener.listen();
    }
}
