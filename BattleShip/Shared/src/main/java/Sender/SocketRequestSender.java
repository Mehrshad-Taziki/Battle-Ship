package Sender;

import Requests.Request;
import Responses.Response;
import Tools.gson.Deserializer;
import Tools.gson.Serializer;
import com.google.gson.*;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketRequestSender implements RequestSender {
    private final Socket socket;
    private final PrintStream printStream;
    private final Scanner scanner;
    private final Gson gson;

    public SocketRequestSender(Socket socket) throws IOException {
        this.socket = socket;
        this.printStream = new PrintStream(socket.getOutputStream(), true);
        this.scanner = new Scanner(socket.getInputStream());
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Response.class, new Deserializer<>())
                .registerTypeAdapter(Request.class, new Serializer<>())
                .create();
    }

    @Override
    public Response sendRequest(Request request) {
        String requestString = gson.toJson(request, Request.class);
        printStream.println(requestString);
        String responseString = scanner.nextLine();
        return gson.fromJson(responseString, Response.class);
    }

    public void close() {
        try {
            printStream.close();
            scanner.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
