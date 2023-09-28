package Requests;

import Responses.Response;

public class ReadyPlayerRequest extends Request{
    @Override
    public Response takeAct(RequestHandler requestHandler) {
        return requestHandler.readyPlayer();
    }
}
