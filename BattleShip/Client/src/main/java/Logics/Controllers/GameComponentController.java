package Logics.Controllers;

import Graphics.Views.GameComponentView;
import Logics.LogicalAgent;
import Requests.WatchGameRequest;
import model.GameSquad;
import model.Stat;

public class GameComponentController {
    private final GameComponentView view;
    private final LogicalAgent logicalAgent;
    private String player1;
    private String player2;
    private Stat stat;

    public GameComponentController(GameComponentView view, LogicalAgent logicalAgent) {
        this.view = view;
        this.logicalAgent = logicalAgent;
    }

    public void load(String player1Name, String player2Name, Stat stat) {
        view.load(player1Name, player2Name, stat);
        this.player1 = player1Name;
        this.player2 = player2Name;
        this.stat = stat;
        loadListeners();
    }

    private void loadListeners() {
        view.setWatchGameListener(() -> logicalAgent.addRequest(new WatchGameRequest(new GameSquad(player1, player2, stat))));
    }
}
