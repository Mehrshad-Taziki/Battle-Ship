package Responses;

import Enums.AlertType;
import model.BattleShipBoard;
import model.GameSquad;
import model.Player;

import java.util.ArrayList;
import java.util.HashSet;

public interface ResponseHandler {
    void showAlert(String message, AlertType type);

    void signPlayer(int token);

    void showGameBoard(BattleShipBoard yourBoard, BattleShipBoard opponentBoard, String turnText, Player clientPlayer, Player opponentPlayer, int time);

    void showWaitingBoard();

    void showEditBoard(BattleShipBoard board, int time);

    void showEndGameBoard(boolean isWin);

    void playerIsReady();

    void showListOfGames(HashSet<GameSquad> games);

    void watchGame(BattleShipBoard playerOneBoard, BattleShipBoard playerTwoBoard, Player playerOne, Player playerTwo);

    void updateLeaderBoard(ArrayList<Player> topWin, ArrayList<Player> topLose, ArrayList<Player> topGamesPlayed, ArrayList<Player> topPoint);

    void logOut();

    void showStats(Player player);
}
