package config;

public class Alerts {
    private static final Config alertsConfig = Config.getConfig("alerts");

    public static String finish() {
        return alertsConfig.getProperty(String.class, "finish");
    }

    public static String password() {
        return alertsConfig.getProperty(String.class, "password");

    }

    public static String username() {
        return alertsConfig.getProperty(String.class, "username");

    }

    public static String noAttempt() {
        return alertsConfig.getProperty(String.class, "noAttempt");
    }

    public static String turn() {
        return alertsConfig.getProperty(String.class, "turn");

    }

    public static String online() {
        return alertsConfig.getProperty(String.class, "online");

    }
}
