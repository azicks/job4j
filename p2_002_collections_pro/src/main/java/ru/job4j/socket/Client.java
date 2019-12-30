package ru.job4j.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class Client {
    private final Socket socket;
    private static final int PORT = 5050;
    private static final String IP = "127.0.0.1";

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        Scanner console = new Scanner(System.in);
        String command, answer;
        boolean active = true;
        do {
            do {
                answer = in.readLine();
                if (Server.Commands.BYE.equals(answer)) {
                    active = false;
                }
                if ("".equals(answer)) {
                    break;
                }
                System.out.println(answer);
            }
            while (true);
            if (!active) {
                break;
            }
            command = console.nextLine();
            out.println(command);
        }
        while (true);
    }

    public static void main(String[] args) {
        try (Socket socket = new Socket(InetAddress.getByName(IP), PORT)) {
            Client client = new Client(socket);
            client.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
