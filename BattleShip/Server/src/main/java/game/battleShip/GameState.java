package game.battleShip;

import Enums.Side;
import model.BattleShipBoard;
import model.Ship;
import model.Stat;

public class GameState {
    private boolean playerOneReady = false;
    private boolean playerTwoReady = false;
    private boolean inProgress = true;
    private int turnsPlayed;
    private int playerOneRandoms = 3;
    private int playerTwoRandoms = 3;
    private final BattleShipBoard playerOneBoard;
    private final BattleShipBoard playerTwoBoard;
    private Side turn;


    public GameState() {
        this.turnsPlayed = 0;
        turn = Side.PLAYER_ONE;
        playerOneBoard = new BattleShipBoard();
        playerTwoBoard = new BattleShipBoard();
    }

    public boolean isPlayerOneReady() {
        return playerOneReady;
    }

    public void setPlayerReady(Side side) {
        if (side == Side.PLAYER_ONE) this.playerOneReady = true;
        if (side == Side.PLAYER_TWO) this.playerTwoReady = true;

    }

    public boolean isPlayerTwoReady() {
        return playerTwoReady;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public BattleShipBoard getPlayerOneBoard(boolean hasAccess) {
        if (hasAccess) {
            return playerOneBoard;
        }
        return new BattleShipBoard(playerOneBoard);
    }

    public BattleShipBoard getPlayerTwoBoard(boolean hasAccess) {
        if (hasAccess) {
            return playerTwoBoard;
        }

        return new BattleShipBoard(playerTwoBoard);


    }

    public boolean randomized(Side side) {
        if (side == Side.PLAYER_ONE) {
            if (playerOneRandoms > 0) {
                playerOneRandoms--;
                return true;
            }
            return false;
        }
        if (side == Side.PLAYER_TWO) {
            if (playerTwoRandoms > 0) {
                playerTwoRandoms--;
                return true;
            }
            return false;
        }
        return false;
    }

    public void changeTurn() {
        if (turn == Side.PLAYER_ONE) turn = Side.PLAYER_TWO;
        else turn = Side.PLAYER_ONE;
        turnsPlayed++;
    }

    public void endGame() {
        inProgress = false;
    }

    public Side getTurn() {
        return turn;
    }

    public int getDestroyedShips(Side side) {
        int count = 0;
        if (side == Side.PLAYER_ONE) {
            BattleShipBoard board = getPlayerOneBoard(true);
            for (Ship ship : board.getShips()) {
                if (ship.getHealth() <= 0) count++;
            }
        }
        if (side == Side.PLAYER_TWO) {
            BattleShipBoard board = getPlayerTwoBoard(true);
            for (Ship ship : board.getShips()) {
                if (ship.getHealth() <= 0) count++;
            }
        }
        return count;
    }


    public Stat getStats() {
        int playerOneCount = getDestroyedShips(Side.PLAYER_ONE);
        int playerTwoCount = getDestroyedShips(Side.PLAYER_TWO);
        return new Stat(turnsPlayed, 10 - playerOneCount, 10 - playerTwoCount, playerOneCount, playerTwoCount);
    }
}
