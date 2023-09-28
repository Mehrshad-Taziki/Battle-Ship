package model;

public class Stat {
    private final int turnsPlayed;
    private final int playerOneFixShips;
    private final int playerTwoFixShips;
    private final int playerOneDestroyedShips;
    private final int playerTwoDestroyedShips;

    public Stat(int turnsPlayed, int playerOneFixShips, int playerTwoFixShips, int playerOneDestroyedShips, int playerTwoDestroyedShips) {
        this.turnsPlayed = turnsPlayed;
        this.playerOneFixShips = playerOneFixShips;
        this.playerTwoFixShips = playerTwoFixShips;
        this.playerOneDestroyedShips = playerOneDestroyedShips;
        this.playerTwoDestroyedShips = playerTwoDestroyedShips;
    }

    public int getTurnsPlayed() {
        return turnsPlayed;
    }

    public int getPlayerOneFixShips() {
        return playerOneFixShips;
    }

    public int getPlayerTwoFixShips() {
        return playerTwoFixShips;
    }

    public int getPlayerOneDestroyedShips() {
        return playerOneDestroyedShips;
    }

    public int getPlayerTwoDestroyedShips() {
        return playerTwoDestroyedShips;
    }
}
