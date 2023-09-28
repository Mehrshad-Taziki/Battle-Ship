package Responses;

public class SignPlayer extends Response{
    private final int token;

    public SignPlayer(int token) {
        this.token = token;
    }

    @Override
    public void takeAct(ResponseHandler responseHandler) {
        responseHandler.signPlayer(token);
    }
}
