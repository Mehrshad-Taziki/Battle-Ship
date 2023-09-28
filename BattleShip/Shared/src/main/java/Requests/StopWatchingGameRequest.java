package Requests;

import Responses.Response;

public class StopWatchingGameRequest extends Request{
    @Override
    public Response takeAct(RequestHandler requestHandler) {
        return requestHandler.stopWatching();
    }
}
