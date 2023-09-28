package Responses;

import model.BattleShipBoard;
import model.Player;

public class BoardResponse extends Response {
    private final BattleShipBoard clientBoard;
    private final BattleShipBoard opponentBoard;
    private final Player clientPlayer;
    private final Player opponentPlayer;
    private final String turnText;
    private final int time;


    public BoardResponse(BattleShipBoard clientBoard, BattleShipBoard opponentBoard, String turnText, Player clientPlayer, Player opponentPlayer, int time) {
        this.turnText = turnText;
        this.clientBoard = clientBoard;
        this.opponentBoard = opponentBoard;
        this.clientPlayer = clientPlayer;
        this.opponentPlayer = opponentPlayer;
        this.time = time;
    }

    @Override
    public void takeAct(ResponseHandler responseHandler) {
        responseHandler.showGameBoard(clientBoard, opponentBoard, turnText, clientPlayer, opponentPlayer, time);
    }

}
