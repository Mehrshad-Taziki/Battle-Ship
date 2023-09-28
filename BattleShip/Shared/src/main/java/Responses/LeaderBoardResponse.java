package Responses;

import model.Player;

import java.util.ArrayList;

public class LeaderBoardResponse extends Response {
    private final ArrayList<Player> topWin;
    private final ArrayList<Player> topLose;
    private final  ArrayList<Player> topGamesPlayed;
    private final ArrayList<Player> topPoints;

    public LeaderBoardResponse(ArrayList<Player> topWin, ArrayList<Player> topLose, ArrayList<Player> topGamesPlayed, ArrayList<Player> topPoints) {
        this.topWin = topWin;
        this.topLose = topLose;
        this.topGamesPlayed = topGamesPlayed;
        this.topPoints = topPoints;
    }

    @Override
    public void takeAct(ResponseHandler responseHandler) {
        responseHandler.updateLeaderBoard(topWin, topLose, topGamesPlayed, topPoints);
    }
}
