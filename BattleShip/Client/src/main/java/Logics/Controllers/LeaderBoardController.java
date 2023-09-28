package Logics.Controllers;

import Graphics.Views.LeaderBoardView;
import Logics.LogicalAgent;
import model.Player;

import java.util.ArrayList;

public class LeaderBoardController {
    private final LeaderBoardView view;
    private final LogicalAgent logicalAgent;

    public LeaderBoardController(LeaderBoardView view, LogicalAgent logicalAgent) {
        this.view = view;
        this.logicalAgent = logicalAgent;
        loadListeners();
    }

    public void loadLeaderBoard(ArrayList<Player> topWin, ArrayList<Player> topLose, ArrayList<Player> topGamesPlayed, ArrayList<Player> topPoint) {
        view.load(topWin, topLose, topGamesPlayed, topPoint);
    }

    private void loadListeners(){
        view.loadBackListener( () -> logicalAgent.signPlayer(0));
    }


}
