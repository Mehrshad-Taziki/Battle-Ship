package Responses;

import model.Player;

public class PlayerResponse extends Response{
    private final Player player;

    public PlayerResponse(Player player) {
        this.player = player;
    }

    @Override
    public void takeAct(ResponseHandler responseHandler) {
        responseHandler.showStats(player);
    }
}
