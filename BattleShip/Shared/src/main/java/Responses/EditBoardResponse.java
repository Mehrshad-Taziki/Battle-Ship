package Responses;

import model.BattleShipBoard;

public class EditBoardResponse extends Response {
    private final BattleShipBoard board;
    private final int time;

    public EditBoardResponse(BattleShipBoard board, int time) {
        this.board = board;
        this.time = time;
    }

    @Override
    public void takeAct(ResponseHandler responseHandler) {
        responseHandler.showEditBoard(board, time);
    }

    public BattleShipBoard getBoard() {
        return board;
    }
}
