package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target));
             BufferedReader in = new BufferedReader(new FileReader(source))) {
            String beginUnavailable = "no";
            boolean unavailable = false;
            String line = in.readLine();
            while (line != null) {
                if (!line.isEmpty()) {
                    String[] data = line.split(" ");
                    if (!unavailable && (data[0].equals("500") || data[0].equals("400"))) {
                        beginUnavailable = data[1];
                        unavailable = true;
                    } else if (unavailable && !(data[0].equals("500") || data[0].equals("400"))) {
                        out.println(beginUnavailable + ";" + data[1]);
                        unavailable = false;
                    }
                }
                line = in.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Analizy().unavailable("./p2_002_collections_pro/data/server.status",
                "./p2_002_collections_pro/data/unavailable.csv");
    }
}