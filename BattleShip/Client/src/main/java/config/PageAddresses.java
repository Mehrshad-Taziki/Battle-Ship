package config;

public class PageAddresses {
    private static final Config scenesConfig = Config.getConfig("mainConfig");
    private static final String WELCOME_PAGE_ADDRESS = scenesConfig.getProperty("welcomePage");
    private static final String REGISTER_PAGE_ADDRESS = scenesConfig.getProperty("registerPage");
    private static final String LOGIN_PAGE_ADDRESS = scenesConfig.getProperty("loginPage");
    private static final String MAIN_PAGE_ADDRESS = scenesConfig.getProperty("mainMenu");
    private static final String WAITING_PAGE_ADDRESS = scenesConfig.getProperty("waitingPage");
    private static final String EDIT_PAGE_ADDRESS = scenesConfig.getProperty("editPage");
    private static final String GAME_PAGE_ADDRESS = scenesConfig.getProperty("gamePage");
    private static final String END_PAGE_ADDRESS = scenesConfig.getProperty("endPage");
    private static final String GAME_LIST_ADDRESS = scenesConfig.getProperty("gameList");
    private static final String WATCH_PAGE_ADDRESS = scenesConfig.getProperty("watchGame");
    private static final String LEADERBOARD_ADDRESS = scenesConfig.getProperty("leaderBoard");
    private static final String STATS_PAGE_ADDRESS = scenesConfig.getProperty("stats");

    public static String register() {
        return REGISTER_PAGE_ADDRESS;
    }

    public static String login() {
        return LOGIN_PAGE_ADDRESS;
    }

    public static String welcome() {
        return WELCOME_PAGE_ADDRESS;
    }

    public static String main() {
        return MAIN_PAGE_ADDRESS;
    }

    public static String waiting() {
        return WAITING_PAGE_ADDRESS;
    }

    public static String edit() {
        return EDIT_PAGE_ADDRESS;
    }

    public static String game() {
        return GAME_PAGE_ADDRESS;
    }

    public static String end() {
        return END_PAGE_ADDRESS;
    }

    public static String gameList() {
        return GAME_LIST_ADDRESS;
    }

    public static String watch() {
        return WATCH_PAGE_ADDRESS;
    }

    public static String leaderBoard() {
        return LEADERBOARD_ADDRESS;
    }

    public static String stat() {
        return STATS_PAGE_ADDRESS;
    }

}
