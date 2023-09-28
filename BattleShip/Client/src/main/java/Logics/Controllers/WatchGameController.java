package Logics.Controllers;

import Graphics.Views.WatchGameView;
import Logics.LogicalAgent;
import Requests.StopWatchingGameRequest;
import model.BattleShipBoard;
import model.Player;

public class WatchGameController {
    private final WatchGameView view;
    private final LogicalAgent logicalAgent;

    public WatchGameController(WatchGameView view, LogicalAgent logicalAgent) {
        this.view = view;
        this.logicalAgent = logicalAgent;
        loadListeners();
    }

    public void buildBoard(BattleShipBoard playerOneBoard, BattleShipBoard playerTwoBoard, Player playerOne, Player playerTwo) {
        view.buildBoard(playerOneBoard, playerTwoBoard, playerOne, playerTwo);
    }

    private void loadListeners() {
        view.setBackListener(() -> logicalAgent.addRequest(new StopWatchingGameRequest()));
    }
}
