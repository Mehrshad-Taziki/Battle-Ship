package Responses;

public class WaitingBoardResponse extends Response{
    @Override
    public void takeAct(ResponseHandler responseHandler) {
        responseHandler.showWaitingBoard();
    }
}
