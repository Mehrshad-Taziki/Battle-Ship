package Logics.Controllers;

import Graphics.Views.MainMenuView;
import Logics.LogicalAgent;
import Requests.*;

public class MainMenuController {
    private final MainMenuView view;
    private final LogicalAgent logicalAgent;

    public MainMenuController(MainMenuView view, LogicalAgent logicalAgent) {
        this.view = view;
        this.logicalAgent = logicalAgent;
        loadListeners();
    }

    private void loadListeners() {
        view.loadPlayListener(() -> {
            logicalAgent.showWaitingBoard();
            logicalAgent.addRequest(new FindGameRequest());
        });
        view.loadLogOutListener(() -> logicalAgent.addRequest(new LogOutRequest()));
        view.loadWatchGameListener(() -> logicalAgent.addRequest(new ListOfGamesRequest()));
        view.loadLeaderBoardListener(() -> logicalAgent.addRequest(new LeaderBoardRequest()));
        view.loadStatsListener(() -> logicalAgent.addRequest(new GetPlayerRequest()));

    }
}