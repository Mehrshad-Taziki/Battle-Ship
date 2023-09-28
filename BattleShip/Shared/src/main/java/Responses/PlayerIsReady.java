package Responses;

public class PlayerIsReady extends Response{
    @Override
    public void takeAct(ResponseHandler responseHandler) {
        responseHandler.playerIsReady();
    }
}
