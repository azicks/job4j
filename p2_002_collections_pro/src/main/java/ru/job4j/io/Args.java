package ru.job4j.io;

public class Args {
    private String directory;
    private String exclude;
    private String output;
    private String name;
    private boolean mask;
    private boolean full;
    private boolean regex;

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
            if (args[i].equals("-n")) {
                name = args[++i];
            }
            if (args[i].equals("-m")) {
                mask = true;
            }
            if (args[i].equals("-f")) {
                full = true;
            }
            if (args[i].equals("-r")) {
                regex = true;
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

    public boolean isMask() {
        return mask;
    }

    public boolean isFull() {
        return full;
    }

    public boolean isRegex() {
        return regex;
    }

    public String name() {
        return name;
    }
}
