package Graphics.Views;


import Listener.Listener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EndGameBoardView {
    private Listener listener;
    @FXML
    private Label winLabel;

    @FXML
    void backToMainMenu() {
        listener.listen();
    }

    public void loadWinLabel(boolean isWonTheGame) {
        Platform.runLater(() -> {
            if (isWonTheGame) winLabel.setText("You Won The Game :D");
            else winLabel.setText("You Lost The Game :(");
        });
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
}
