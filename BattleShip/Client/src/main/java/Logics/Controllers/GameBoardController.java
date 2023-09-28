package Logics.Controllers;

import Graphics.Views.GameBoardView;
import Logics.LogicalAgent;
import Requests.MouseClicked;
import model.BattleShipBoard;
import model.Player;

public class GameBoardController {
    private final GameBoardView view;
    private final  LogicalAgent logicalAgent;

    public GameBoardController(GameBoardView view, LogicalAgent logicalAgent) {
        this.view = view;
        this.logicalAgent = logicalAgent;
        loadListener();
    }

    private void loadListener(){
        view.setCellListener((i, j) -> logicalAgent.addRequest(new MouseClicked(i, j)));
    }

    public void loadBoard(BattleShipBoard clientBoard, BattleShipBoard enemyBoard, String turnText, Player clientPlayer, Player opponentPlayer, int time){
        view.buildBoard(clientBoard, enemyBoard, turnText, clientPlayer, opponentPlayer, time);
    }
}
