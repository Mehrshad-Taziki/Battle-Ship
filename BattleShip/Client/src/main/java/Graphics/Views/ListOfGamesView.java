package Graphics.Views;

import Listener.Listener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class ListOfGamesView {
    private Listener backListener;
    @FXML
    private GridPane listOfGamesPane;

    public void buildGames(ArrayList<AnchorPane> games){
        Platform.runLater(() -> {
            for (AnchorPane anchorPane :
                    games) {
                listOfGamesPane.add(anchorPane, 0, listOfGamesPane.getRowCount() + 1);
            }
        });
    }
    public void loadBackListener(Listener listener){
        this.backListener = listener;
    }
    @FXML
    void back() {
        backListener.listen();
    }

}
