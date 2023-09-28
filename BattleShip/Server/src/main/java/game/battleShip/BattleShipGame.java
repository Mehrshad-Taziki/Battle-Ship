package game.battleShip;

import Enums.Side;
import Tools.TimeTask;
import model.BattleShipBoard;
import model.Player;
import model.Stat;

public class BattleShipGame {
    private GameState gameState;
    private Side winner;
    private  Player playerOne;
    private Player playerTwo;
    private TimeTask playerOneReady;
    private TimeTask playerTwoReady;
    private TimeTask nextTurn;
    private boolean started = false;


    public BattleShipGame() {
        gameState = new GameState();
    }

    public void clickedCell(int i, int j, Side side) {
        BattleShipBoard board = getBoard(side.getOther());
        boolean hit = board.destroyed(i, j);
        if (!hit) {
            nextTurn();
        }
    }

    public void startTurnTimer() {
        if (nextTurn != null) {
            nextTurn.stop();
        }
        nextTurn = new TimeTask(25, this::nextTurn);
        nextTurn.start();
    }

    public void startReadyTimers() {
        if (!started) {
            playerOneReady = new TimeTask(30, this::readyPlayerOne);
            playerTwoReady = new TimeTask(30, this::readyPlayerTwo);
            playerOneReady.start();
            playerTwoReady.start();
        }
        started = true;
    }

    public boolean isPlayerOneReady() {
        return gameState.isPlayerOneReady();
    }

    public boolean isPlayerTwoReady() {
        return gameState.isPlayerTwoReady();
    }

    public void readyPlayerOne() {
        gameState.setPlayerReady(Side.PLAYER_ONE);
    }

    public void readyPlayerTwo() {
        gameState.setPlayerReady(Side.PLAYER_TWO);
    }

    public boolean isInProgress() {
        return gameState.isInProgress();
    }

    public BattleShipBoard getBoard(Side side) {
        if (side == Side.PLAYER_ONE) {
            return gameState.getPlayerOneBoard(true);
        }
        return gameState.getPlayerTwoBoard(true);
    }

    public BattleShipBoard getOpponentBoard(Side side) {
        if (side == Side.PLAYER_ONE) {
            return gameState.getPlayerTwoBoard(false);
        }
        return gameState.getPlayerOneBoard(false);
    }

    public GameState getGameState() {
        return gameState;
    }

    public Side getWinner() {
        return winner;
    }

    public void setWinner(Side winner) {
        this.winner = winner;
        gameState.endGame();
    }

    public String getTurnText(Side side) {
        if (side == gameState.getTurn()) return "Your Turn...";
        return "Enemy Turn...";
    }

    public void checkForEndGame() {
        BattleShipBoard board2 = getBoard(Side.PLAYER_TWO);
        Side winner = Side.PLAYER_ONE;
        for (int i = 0; i < 10; i++) {
            if (board2.getShips()[i].getHealth() > 0) {
                winner = Side.PLAYER_TWO;
                break;
            }
        }
        if (winner == Side.PLAYER_TWO) {
            BattleShipBoard board1 = getBoard(Side.PLAYER_ONE);
            for (int i = 0; i < 10; i++) {
                if (board1.getShips()[i].getHealth() > 0) {
                    winner = null;
                    break;
                }
            }
        }
        if (winner != null) {
            this.winner = winner;
            gameState.endGame();
        }
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public Player getPlayer(Side side) {
        if (side == Side.PLAYER_ONE) return playerOne;
        return playerTwo;
    }

    public Player getOpponent(Side side) {
        if (side == Side.PLAYER_ONE) return playerTwo;
        return playerOne;
    }

    public void addTime(Side side) {
        if (side == Side.PLAYER_ONE) playerOneReady.addTime(10);
        if (side == Side.PLAYER_TWO) playerTwoReady.addTime(10);
    }

    public int getTime(Side side) {
        if (side == Side.PLAYER_ONE) return playerOneReady.getTime();
        if (side == Side.PLAYER_TWO) return playerTwoReady.getTime();
        return 0;
    }

    public void nextTurn() {
        startTurnTimer();
        gameState.changeTurn();
    }

    public Stat getStats() {
        return gameState.getStats();
    }

    public int getTime() {
        return nextTurn.getTime();
    }
}
