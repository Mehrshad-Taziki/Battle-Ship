package Graphics.Views;

import Enums.CellStatus;
import Listener.Listener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.BattleShipBoard;

public class EditBoardView {
    private Listener randomizeListener;
    private Listener readyListener;
    @FXML
    private GridPane boardPane;
    @FXML
    private Button readyButton;
    @FXML
    private Button randomizeButton;
    @FXML
    private Label attemptCount;
    @FXML
    private Label timeLabel;

    public void buildBoard(BattleShipBoard board, int time) {
        Platform.runLater(() -> boardPane.getChildren().clear());
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int finalI = i;
                int finalJ = j;
                if (board.getCells()[i][j] == CellStatus.INTACT) {
                    Platform.runLater(() -> {
                        Rectangle rectangle = new Rectangle(48, 48, Color.BLACK);
                        boardPane.add(rectangle, finalI, finalJ);
                    });
                }
                if (board.getCells()[i][j] == CellStatus.EMPTY) {
                    Platform.runLater(() -> {
                        Rectangle rectangle = new Rectangle(48, 48, Color.WHITE);
                        boardPane.add(rectangle, finalI, finalJ);
                    });
                }
            }
        }
        Platform.runLater(() -> attemptCount.setText(String.valueOf(board.getCount())));
        Platform.runLater(() -> timeLabel.setText(String.valueOf(time)));
        if (time == 0) readyPlayer();

    }

    public void setRandomizeListener(Listener randomizeListener) {
        this.randomizeListener = randomizeListener;
    }

    public void setReadyListener(Listener readyListener) {
        this.readyListener = readyListener;
    }

    public void ready() {
        readyListener.listen();
    }

    public void randomize() {
        randomizeListener.listen();
    }

    public void readyPlayer() {
        readyButton.setDisable(true);
        randomizeButton.setVisible(false);
    }
}
