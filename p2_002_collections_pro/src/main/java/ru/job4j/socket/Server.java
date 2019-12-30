package ru.job4j.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Server {
    private final Socket socket;
    private static final int PORT = 5050;

    class Commands {
        final static String HELLO = "hello";
        final static String EXIT = "exit";
        final static String WAIT = "wait command...";
        final static String WRONG = "wrong request";
        final static String BYE = "bye";
    }

    public Server(Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        String ask;
        out.println(Commands.WAIT);
        out.println("");
        do {
            ask = in.readLine();
            if (Commands.HELLO.equals(ask)) {
                out.println("Hello, dear friend, I'm a oracle.");
                out.println("");
            } else if (!Commands.EXIT.equals(ask)) {
                out.println(Commands.WRONG);
                out.println("");
            }
        } while (!Commands.EXIT.equals(ask));
        out.println(Commands.BYE);
        out.println("");
    }

    public static void main(String[] args) {
        try (final Socket socket = new ServerSocket(PORT).accept()) {
            Server server = new Server(socket);
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
