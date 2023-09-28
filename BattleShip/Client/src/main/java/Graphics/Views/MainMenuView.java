package Graphics.Views;

import Listener.Listener;

public class MainMenuView {
    private Listener playListener;
    private Listener watchGameListener;
    private Listener leaderBoardListener;
    private  Listener statsListener;
    private  Listener logOutListener;

    public void play() {
        playListener.listen();
    }

    public void watchGame() {
        watchGameListener.listen();
    }

    public void leaderBoard() {
        leaderBoardListener.listen();
    }

    public void loadPlayListener(Listener listener) {
        this.playListener = listener;
    }

    public void loadWatchGameListener(Listener listener) {
        this.watchGameListener = listener;
    }

    public void loadLeaderBoardListener(Listener listener) {
        this.leaderBoardListener = listener;
    }

    public void loadLogOutListener(Listener listener) {
        this.logOutListener = listener;
    }

    public void loadStatsListener(Listener listener) {
        this.statsListener = listener;
    }

    public void logOut() {
        logOutListener.listen();
    }


    public void stats() {
        statsListener.listen();
    }

}
