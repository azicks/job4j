package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Chat {
    private List<String> phrases = new ArrayList<>();
    private boolean alive = true;
    private int size;
    private PrintWriter log;

    private boolean load(String source, String logFile) {
        boolean result = false;
        //Load bot phrases
        try (BufferedReader br = new BufferedReader(new FileReader(source))) {
            String line = br.readLine();
            while (line != null) {
                phrases.add(line);
                line = br.readLine();
            }
            result = !phrases.isEmpty();
            size = phrases.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Open log file
        try {
            this.log = new PrintWriter(new FileOutputStream(logFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void begin() {
        Scanner in = new Scanner(System.in);
        String message;
        do {
            System.out.print("Вы: ");
            message = in.nextLine();
            log.println("Вы: " + message);
            if (message.equals("стоп")) {
                alive = false;
            }
            if (message.equals("продолжить")) {
                alive = true;
            }
            if (alive) {
                String answer = "Собеседник: " + phrases.get((int) (Math.random() * this.size));
                System.out.println(answer);
                log.println(answer);
            }
        } while (!message.equals("закончить"));
        log.close();
    }

    public static void main(String[] args) {
        Chat chat = new Chat();
        String phrasesFile = "./p2_002_collections_pro/data/chatbot.txt";
        String logFile = "./p2_002_collections_pro/data/log.txt";
        if (chat.load(phrasesFile, logFile)) {
            chat.begin();
        }
    }
}
