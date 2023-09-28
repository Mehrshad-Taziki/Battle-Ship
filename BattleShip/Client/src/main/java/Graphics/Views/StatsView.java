package Graphics.Views;

import Listener.Listener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Player;

public class StatsView {
    private Listener backListener;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label gamesLabel;

    @FXML
    private Label pointLabel;

    @FXML
    private Label winsLabel;

    @FXML
    private Label losesLabel;

    public void load(Player player) {
        Platform.runLater(() -> {
            usernameLabel.setText(player.getName());
            gamesLabel.setText(String.valueOf(player.getGamesPlayed()));
            winsLabel.setText(String.valueOf(player.getWins()));
            losesLabel.setText(String.valueOf(player.getLosses()));
            pointLabel.setText(String.valueOf(player.getPoint()));
        });
    }

    public void loadBackListener(Listener listener) {
        this.backListener = listener;
    }

    public void back() {
        backListener.listen();
    }

}
