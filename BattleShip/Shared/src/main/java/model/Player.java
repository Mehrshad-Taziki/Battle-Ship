package model;

import Enums.Status;

public class Player {
    private final String name;
    private int wins;
    private int losses;
    private int gamesPlayed;
    private Status status;


    public Player(String name, int wins, int losses, int gamesPlayed) {
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.gamesPlayed = gamesPlayed;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void addWin() {
        this.gamesPlayed++;
        this.wins++;
    }

    public void addLoss() {
        this.gamesPlayed++;
        this.losses++;
    }

    public int getPoint() {
        return (wins - losses);
    }

    public Status getStatus(){
        return status;
    }

    public void setStatus(Status status){
        this.status = status;
    }
}
