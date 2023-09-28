package Requests;

import Responses.Response;

public class BoardUpdateRequest extends Request{
    @Override
    public Response takeAct(RequestHandler requestHandler) {
        return requestHandler.boardUpdate();
    }
}
