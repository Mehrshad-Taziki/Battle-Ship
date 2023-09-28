package Responses;

import model.GameSquad;

import java.util.HashSet;

public class ListOfGamesResponse extends Response{
    private final HashSet<GameSquad> games;

    public ListOfGamesResponse(HashSet<GameSquad> games) {
        this.games = games;
    }

    public HashSet<GameSquad> getGames() {
        return games;
    }

    @Override
    public void takeAct(ResponseHandler responseHandler) {
        responseHandler.showListOfGames(games);
    }
}
