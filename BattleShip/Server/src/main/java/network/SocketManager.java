package network;

import config.Config;
import game.GameLobby;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketManager extends Thread{
    private static final Config CONNECTION_CONFIG = Config.getConfig("connection");
    int port;
    @Override
    public void run() {
        try {
            port = CONNECTION_CONFIG.getProperty(Integer.class, "port");
            if (port == 0) port = 5555;
            ServerSocket serverSocket = new ServerSocket(port);
            GameLobby gameLobby = new GameLobby();
            while (true){
                Socket socket = serverSocket.accept();
                new ClientHandler(new SocketResponseSender(socket), gameLobby).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
