package Requests;

import Responses.Response;
import model.GameSquad;

public interface RequestHandler {
    Response login(LoginRequest request);

    Response register(RegisterRequest request);

    Response findGame(FindGameRequest request);

    Response cellClicked(int i, int j);

    Response boardUpdate();

    Response randomizeBoard();

    Response readyPlayer();

    Response gameClosed();

    Response getListOfGames();

    Response watchGame(GameSquad squad);

    Response updateWatchGameBoard();

    Response stopWatching();

    Response updateLeaderBoard();

    Response logout();

    Response getPLayer();
}
