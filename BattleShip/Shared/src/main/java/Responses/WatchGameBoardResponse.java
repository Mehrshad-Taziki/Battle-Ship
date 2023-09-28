package Responses;

import model.BattleShipBoard;
import model.Player;

public class WatchGameBoardResponse extends Response {
    private final BattleShipBoard playerOneBoard;
    private final  BattleShipBoard playerTwoBoard;
    private final Player playerOne;
    private final  Player playerTwo;

    public WatchGameBoardResponse(BattleShipBoard playerOneBoard, BattleShipBoard playerTwoBoard, Player playerOne, Player playerTwo) {
        this.playerOneBoard = playerOneBoard;
        this.playerTwoBoard = playerTwoBoard;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    @Override
    public void takeAct(ResponseHandler responseHandler) {
        responseHandler.watchGame(playerOneBoard, playerTwoBoard, playerOne, playerTwo);
    }
}
