package Responses;

public class EndGameBoard extends Response {
    private final boolean wonTheGame;

    public EndGameBoard(boolean wonTheGame) {
        this.wonTheGame = wonTheGame;
    }

    @Override
    public void takeAct(ResponseHandler responseHandler) {
        responseHandler.showEndGameBoard(wonTheGame);
    }

    public boolean WonTheGame() {
        return wonTheGame;
    }
}
