package Requests;

import Responses.Response;

public class GetPlayerRequest extends Request{
    @Override
    public Response takeAct(RequestHandler requestHandler) {
        return requestHandler.getPLayer();
    }
}
