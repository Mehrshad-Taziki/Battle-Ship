package Logics.Controllers;

import Graphics.Views.ListOfGamesView;
import Logics.LogicalAgent;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import model.GameSquad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class ListOfGamesController {
    private final ListOfGamesView view;
    private final LogicalAgent logicalAgent;


    public ListOfGamesController(ListOfGamesView view, LogicalAgent logicalAgent) {
        this.view = view;
        this.logicalAgent = logicalAgent;
        loadListeners();
    }

    private void loadListeners(){
        view.loadBackListener( () -> logicalAgent.signPlayer(0));
    }

    public void loadGamesList(HashSet<GameSquad> games) {
        ArrayList<AnchorPane> gamesList = new ArrayList<>();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (GameSquad gameSquad :
                        games) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/Scenes/WelcomePage/Components/GameComponent.fxml"));
                        AnchorPane anchorPane = fxmlLoader.load();
                        new GameComponentController(fxmlLoader.getController(), logicalAgent).load(gameSquad.getPlayerOne(), gameSquad.getPlayerTwo(), gameSquad.getStat());
                        gamesList.add(anchorPane);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        view.buildGames(gamesList);
    }
}
