package Requests;

import Responses.Response;

public class RandomizeRequest extends Request{
    @Override
    public Response takeAct(RequestHandler requestHandler) {
        return requestHandler.randomizeBoard();
    }
}
