package Requests;

import Responses.Response;
import model.GameSquad;

public class WatchGameRequest extends Request{
    private final GameSquad squad;

    public WatchGameRequest(GameSquad squad) {
        this.squad = squad;
    }

    @Override
    public Response takeAct(RequestHandler requestHandler) {
        return requestHandler.watchGame(squad);
    }
}
