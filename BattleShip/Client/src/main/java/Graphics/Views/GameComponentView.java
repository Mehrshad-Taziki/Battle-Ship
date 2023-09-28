package Graphics.Views;

import Listener.Listener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Stat;

public class GameComponentView {
    private Listener watchGameListener;
    @FXML
    private Label playerOneName;

    @FXML
    private Label playerTwoName;

    @FXML
    private Label turnsCount;

    @FXML
    private Label playerOneDShips;

    @FXML
    private Label playerOneIShips;

    @FXML
    private Label playerTwoDShips;

    @FXML
    private Label playerTwoIShips;

    public void load(String player1Name, String player2Name, Stat stat) {
        Platform.runLater(() -> playerOneName.setText(player1Name));
        Platform.runLater(() -> playerTwoName.setText(player2Name));
        Platform.runLater(() -> {
            turnsCount.setText(String.valueOf(stat.getTurnsPlayed()));
            playerOneDShips.setText(String.valueOf(stat.getPlayerOneDestroyedShips()));
            playerTwoDShips.setText(String.valueOf(stat.getPlayerTwoDestroyedShips()));
            playerOneIShips.setText(String.valueOf(stat.getPlayerOneFixShips()));
            playerTwoIShips.setText(String.valueOf(stat.getPlayerTwoFixShips()));
        });
    }


    @FXML
    void watch() {
        watchGameListener.listen();
    }

    public void setWatchGameListener(Listener watchGameListener) {
        this.watchGameListener = watchGameListener;
    }
}
