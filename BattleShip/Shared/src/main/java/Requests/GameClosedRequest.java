package Requests;

import Responses.Response;

public class GameClosedRequest extends Request{
    @Override
    public Response takeAct(RequestHandler requestHandler) {
        return requestHandler.gameClosed();
    }
}
