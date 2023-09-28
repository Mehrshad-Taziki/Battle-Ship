package model;

import java.util.Objects;

public class GameSquad {
    private final String playerOne;
    private final String playerTwo;
    private final Stat stat;

    public GameSquad(String playerOne, String playerTwo, Stat stat) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.stat = stat;
    }

    public String getPlayerOne() {
        return playerOne;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }

    public Stat getStat() {
        return stat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameSquad)) return false;
        GameSquad gameSquad = (GameSquad) o;
        if (Objects.equals(playerOne, gameSquad.playerOne) &&
                Objects.equals(playerTwo, gameSquad.playerTwo)) return true;
        if (Objects.equals(playerOne, gameSquad.playerTwo) &&
                Objects.equals(playerTwo, gameSquad.playerOne)) return true;
        return false;
    }
}
