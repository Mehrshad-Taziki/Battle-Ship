package Requests;

import Responses.Response;

public class LeaderBoardRequest extends Request {
    @Override
    public Response takeAct(RequestHandler requestHandler) {
        return requestHandler.updateLeaderBoard();
    }
}
