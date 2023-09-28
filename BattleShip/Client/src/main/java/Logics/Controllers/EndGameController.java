package Logics.Controllers;

import Graphics.Views.EndGameBoardView;
import Logics.LogicalAgent;
import Requests.GameClosedRequest;

public class EndGameController {
    private final EndGameBoardView view;
    private final LogicalAgent logicalAgent;

    public EndGameController(EndGameBoardView view, LogicalAgent logicalAgent) {
        this.view = view;
        this.logicalAgent = logicalAgent;
        loadListener();
    }

    private void loadListener() {
        view.setListener(() -> logicalAgent.addRequest(new GameClosedRequest()));
    }
}
