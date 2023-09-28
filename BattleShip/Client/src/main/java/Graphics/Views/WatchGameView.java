package Graphics.Views;

import Enums.CellStatus;
import Listener.Listener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.BattleShipBoard;
import model.Player;

public class WatchGameView {
    private Listener backListener;
    @FXML
    private GridPane playerTwoBoard;

    @FXML
    private GridPane playerOneBoard;

    @FXML
    private Label playerOneLabel;

    @FXML
    private Label playerTwoLabel;

    public void buildBoard(BattleShipBoard playerOneBoard, BattleShipBoard playerTwoBoard, Player playerOne, Player playerTwo) {
        Platform.runLater(() -> this.playerOneBoard.getChildren().clear());
        Platform.runLater(() -> this.playerTwoBoard.getChildren().clear());
        Platform.runLater(() -> playerOneLabel.setText(playerOne.getName()));
        Platform.runLater(() -> playerTwoLabel.setText(playerTwo.getName()));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int finalI = i;
                int finalJ = j;
                if (playerOneBoard.getCells()[i][j] == CellStatus.INTACT) {
                    Platform.runLater(() -> {
                        Rectangle rectangle = new Rectangle(38, 38, Color.WHITE);
                        this.playerOneBoard.add(rectangle, finalI, finalJ);
                    });
                }
                if (playerOneBoard.getCells()[i][j] == CellStatus.DESTROYED) {
                    Platform.runLater(() -> {
                        Rectangle rectangle = new Rectangle(38, 38, Color.RED);
                        this.playerOneBoard.add(rectangle, finalI, finalJ);
                    });
                }
                if (playerOneBoard.getCells()[i][j] == CellStatus.EMPTY) {
                    Platform.runLater(() -> {
                        Rectangle rectangle = new Rectangle(38, 38, Color.WHITE);
                        this.playerOneBoard.add(rectangle, finalI, finalJ);
                    });
                }
                if (playerOneBoard.getCells()[i][j] == CellStatus.HIT) {
                    Platform.runLater(() -> {
                        Rectangle rectangle = new Rectangle(38, 38, Color.BLACK);
                        Circle circle = new Circle(5, Color.RED);
                        this.playerOneBoard.add(rectangle, finalI, finalJ);
                        this.playerOneBoard.add(circle, finalI, finalJ);
                    });
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int finalI = i;
                int finalJ = j;
                if (playerTwoBoard.getCells()[i][j] == CellStatus.INTACT) {
                    Platform.runLater(() -> {
                        Rectangle rectangle = new Rectangle(38, 38, Color.WHITE);
                        this.playerTwoBoard.add(rectangle, finalI, finalJ);
                    });
                }
                if (playerTwoBoard.getCells()[i][j] == CellStatus.DESTROYED) {
                    Platform.runLater(() -> {
                        Rectangle rectangle = new Rectangle(38, 38, Color.RED);
                        this.playerTwoBoard.add(rectangle, finalI, finalJ);
                    });
                }
                if (playerTwoBoard.getCells()[i][j] == CellStatus.EMPTY) {
                    Platform.runLater(() -> {
                        Rectangle rectangle = new Rectangle(38, 38, Color.WHITE);
                        this.playerTwoBoard.add(rectangle, finalI, finalJ);
                    });
                }
                if (playerTwoBoard.getCells()[i][j] == CellStatus.HIT) {
                    Platform.runLater(() -> {
                        Rectangle rectangle = new Rectangle(38, 38, Color.BLACK);
                        Circle circle = new Circle(5, Color.RED);
                        this.playerTwoBoard.add(rectangle, finalI, finalJ);
                        this.playerTwoBoard.add(circle, finalI, finalJ);
                    });
                }
            }
        }
    }

    public void back() {
        backListener.listen();
    }

    public void setBackListener(Listener backListener) {
        this.backListener = backListener;
    }
}
