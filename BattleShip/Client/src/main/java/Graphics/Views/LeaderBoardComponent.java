package Graphics.Views;

import Enums.Status;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class LeaderBoardComponent {
    @FXML
    private Label nameLabel;
    @FXML
    private Label pointLabel;
    @FXML
    private Label statusLabel;

    public void loadNameLabel(String name, int point, Status status) {
        Platform.runLater(() -> nameLabel.setText(name));
        Platform.runLater(() -> pointLabel.setText(String.valueOf(point)));
        loadStatus(status);
    }

    public void loadStatus(Status status) {
        if (status == Status.OFFLINE) {
            Platform.runLater(() -> statusLabel.setTextFill(Color.RED));
            Platform.runLater(() -> statusLabel.setText(status.getText()));
        }
        if (status == Status.ONLINE) {
            Platform.runLater(() -> statusLabel.setTextFill(Color.LIMEGREEN));
            Platform.runLater(() -> statusLabel.setText(status.getText()));
        }
    }
}
