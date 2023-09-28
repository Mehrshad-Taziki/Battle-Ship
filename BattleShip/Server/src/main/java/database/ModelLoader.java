package database;

import model.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ModelLoader {
    private static final String address = "src/main/resources/UsersDirectory";

    public static void save(String username, String password, int wins, int loses, int gamesPlayed) {
        try {
            File userFile = new File(address + "\\" + username + ".txt");
            userFile.createNewFile();
            PrintStream printStream = new PrintStream(userFile);
            printStream.println("Username: " + username);
            printStream.println("Password: " + password);
            printStream.println("Wins: " + wins);
            printStream.println("Loses: " + loses);
            printStream.println("GamesPlayed: " + gamesPlayed);
            printStream.flush();
            printStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save(Player player) {
        save(player.getName(), getPassword(player.getName()), player.getWins(), player.getLosses(), player.getGamesPlayed());
    }


    public static boolean isAvailable(String username) {
        File usersFile = new File(address);
        for (File file :
                usersFile.listFiles()) {
            if (file.getName().equals(username + ".txt")) {
                return false;
            }
        }
        return true;
    }

    public static boolean isCorrect(String username, String password) {
        if (isAvailable(username)) {
            return false;
        }
        try {
            File userFile = new File(address + "\\" + username + ".txt");
            Scanner scanner = new Scanner(userFile);
            scanner.next();
            scanner.next();
            scanner.next();
            String realPass = scanner.next();
            return realPass.equals(password);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Player getPlayer(String username) {
        if (isAvailable(username)) {
            return null;
        }
        try {
            File userFile = new File(address + "\\" + username + ".txt");
            Scanner scanner = new Scanner(userFile);
            scanner.next();
            String name = scanner.next();
            scanner.next();
            scanner.next();
            scanner.next();
            int wins = scanner.nextInt();
            scanner.next();
            int loses = scanner.nextInt();
            scanner.next();
            int gamesPlayed = scanner.nextInt();
            return new Player(name, wins, loses, gamesPlayed);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getPassword(String username) {
        if (isAvailable(username)) {
            return "null";
        }
        try {
            File userFile = new File(address + "\\" + username + ".txt");
            Scanner scanner = new Scanner(userFile);
            scanner.next();
            scanner.next();
            scanner.next();
            String pass = scanner.next();
            return pass;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "null";
    }

    public static ArrayList<Player> getPlayers(){
        ArrayList<Player> players = new ArrayList<>();
        File usersFile = new File(address);
        for (File file :
                usersFile.listFiles()) {
            players.add(getPlayer(file.getName().substring(0,file.getName().length()-4)));
        }
        return players;
    }
}
