package Requests;

import Responses.Response;

public class WatchBoardUpdateRequest extends Request{
    @Override
    public Response takeAct(RequestHandler requestHandler) {
        return requestHandler.updateWatchGameBoard();
    }
}
