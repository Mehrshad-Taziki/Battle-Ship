import Logics.LogicalAgent;
import config.Config;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;


public class Main extends Application {
    private static final Config CONNECTION_CONFIG = Config.getConfig("connection");
    static int port;
    static String ip;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            port = CONNECTION_CONFIG.getProperty(Integer.class, "port");
            ip = CONNECTION_CONFIG.getProperty(String.class, "ip");
            if (port == 0) port = 5555;
            if (ip == null) ip = "127.0.0.1";
            Socket socket = new Socket(ip, port);
            new LogicalAgent(socket, stage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}