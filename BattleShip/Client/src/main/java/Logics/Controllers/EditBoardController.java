package Logics.Controllers;

import Graphics.Views.EditBoardView;
import Logics.LogicalAgent;
import Requests.RandomizeRequest;
import Requests.ReadyPlayerRequest;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.BattleShipBoard;

public class EditBoardController {
    private final EditBoardView view;
    private final LogicalAgent logicalAgent;

    public EditBoardController(EditBoardView view, LogicalAgent logicalAgent) {
        this.view = view;
        this.logicalAgent = logicalAgent;
        loadListeners();
    }

    public void loadViewBoard(BattleShipBoard board, int time) {
        view.buildBoard(board, time);
    }

    private void loadListeners() {
        view.setRandomizeListener(() -> logicalAgent.addRequest(new RandomizeRequest()));
        view.setReadyListener(() -> logicalAgent.addRequest(new ReadyPlayerRequest()));
    }

    public void playerIsReady() {
        view.readyPlayer();
    }
}
