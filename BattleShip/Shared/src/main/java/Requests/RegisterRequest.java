package Requests;

import Responses.Response;

public class RegisterRequest extends Request {
    private final String userName, password;

    public RegisterRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public Response takeAct(RequestHandler requestHandler) {
        return requestHandler.register(this);
    }
}
