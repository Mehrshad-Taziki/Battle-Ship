package Requests;

import Responses.Response;

public class ListOfGamesRequest extends Request {
    @Override
    public Response takeAct(RequestHandler requestHandler) {
        return requestHandler.getListOfGames();
    }
}
