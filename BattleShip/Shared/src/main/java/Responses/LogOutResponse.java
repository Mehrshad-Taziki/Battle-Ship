package Responses;

public class LogOutResponse extends Response{
    @Override
    public void takeAct(ResponseHandler responseHandler) {
        responseHandler.logOut();
    }
}
