package ru.job4j.io;

public class Args {
    private String directory;
    private String exclude;
    private String output;

    public Args(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-d")) {
                directory = args[++i];
            }
            if (args[i].equals("-e")) {
                exclude = args[++i];
            }
            if (args[i].equals("-o")) {
                output = args[++i];
            }
        }
    }

    public String directory() {
        return directory;
    }

    public String output() {
        return output;
    }

    public String exclude() {
        return exclude;
    }
}
