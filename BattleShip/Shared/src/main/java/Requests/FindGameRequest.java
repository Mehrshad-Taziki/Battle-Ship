package Requests;

import Responses.Response;

public class FindGameRequest extends Request {
    @Override
    public Response takeAct(RequestHandler requestHandler) {
        return requestHandler.findGame(this);
    }
}
