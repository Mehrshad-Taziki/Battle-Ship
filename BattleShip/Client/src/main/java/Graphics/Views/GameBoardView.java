package Graphics.Views;

import Enums.CellStatus;
import Listener.CellListener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.BattleShipBoard;
import model.Player;

public class GameBoardView {
    private CellListener cellListener;
    @FXML
    private GridPane opponentBoard;

    @FXML
    private GridPane clientBoard;

    @FXML
    private Label turnLabel;

    @FXML
    private Label clientLabel;

    @FXML
    private Label enemyLabel;

    @FXML
    private Label timeLabel;

    public void buildBoard(BattleShipBoard playerBoard, BattleShipBoard enemyBoard, String turnText, Player clientPlayer, Player opponentPlayer, int time) {
        Platform.runLater(() -> clientBoard.getChildren().clear());
        Platform.runLater(() -> opponentBoard.getChildren().clear());
        Platform.runLater(() -> clientLabel.setText(clientPlayer.getName()));
        Platform.runLater(() -> enemyLabel.setText(opponentPlayer.getName()));
        Platform.runLater(() -> timeLabel.setText(String.valueOf(time)));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int finalI = i;
                int finalJ = j;
                if (playerBoard.getCells()[i][j] == CellStatus.INTACT) {
                    Platform.runLater(() -> {
                        Rectangle rectangle = new Rectangle(38, 38, Color.BLACK);
                        clientBoard.add(rectangle, finalI, finalJ);
                    });
                }
                if (playerBoard.getCells()[i][j] == CellStatus.DESTROYED) {
                    Platform.runLater(() -> {
                        Rectangle rectangle = new Rectangle(38, 38, Color.RED);
                        clientBoard.add(rectangle, finalI, finalJ);
                    });
                }
                if (playerBoard.getCells()[i][j] == CellStatus.EMPTY) {
                    Platform.runLater(() -> {
                        Rectangle rectangle = new Rectangle(38, 38, Color.WHITE);
                        clientBoard.add(rectangle, finalI, finalJ);
                    });
                }
                if (playerBoard.getCells()[i][j] == CellStatus.HIT) {
                    Platform.runLater(() -> {
                        Rectangle rectangle = new Rectangle(38, 38, Color.BLACK);
                        Circle circle = new Circle(5, Color.RED);
                        clientBoard.add(rectangle, finalI, finalJ);
                        clientBoard.add(circle, finalI, finalJ);
                    });
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int finalI = i;
                int finalJ = j;
                if (enemyBoard.getCells()[i][j] == CellStatus.DESTROYED) {
                    Platform.runLater(() -> {
                        Rectangle rectangle = new Rectangle(38, 38, Color.RED);
                        opponentBoard.add(rectangle, finalI, finalJ);
                    });
                }
                else if (enemyBoard.getCells()[i][j] == CellStatus.HIT) {
                    Platform.runLater(() -> {
                        Rectangle rectangle = new Rectangle(38, 38, Color.BLACK);
                        Circle circle = new Circle(5, Color.RED);
                        opponentBoard.add(rectangle, finalI, finalJ);
                        opponentBoard.add(circle, finalI, finalJ);
                    });
                } else {
                    Platform.runLater(() -> {
                        Rectangle rectangle = new Rectangle(38, 38, Color.WHITE);
                        rectangle.setOnMouseClicked((event) -> cellListener.listen(finalI, finalJ));
                        opponentBoard.add(rectangle, finalI, finalJ);
                    });
                }
            }
        }
        Platform.runLater(() -> turnLabel.setText(turnText));
    }

    public void setCellListener(CellListener cellListener) {
        this.cellListener = cellListener;
    }

}
