package ru.job4j.io;

import java.io.*;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class Find {
    private final static int MASK = 1;
    private final static int FULL = 2;
    private final static int REGEX = 3;
    private File root;
    private File output;
    private int type;
    private String mask;

    public void find() {
        try (PrintWriter result = new PrintWriter(new FileOutputStream(this.output))) {
            Queue<File> files = new LinkedList<>();
            files.offer(this.root);
            while (!files.isEmpty()) {
                File f = files.poll();
                if (!f.isDirectory()) {
                    this.compare(f).ifPresent(result::println);
                    //this.compare(f).ifPresent(System.out::println);
                }
                File[] children = f.listFiles();
                if (!(children == null)) {
                    for (File child : f.listFiles()) {
                        files.offer(child);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Optional<String> compare(File file) {
        Optional<String> result = Optional.empty();
        String name = file.getName();
        if (this.type == FULL && this.mask.equals(name)) {
            result = Optional.of(file.getAbsolutePath());
        }
        if (this.type == MASK && maskMatches(name, this.mask)) {
            result = Optional.of(file.getAbsolutePath());
        }
        if (this.type == REGEX && name.matches(this.mask)) {
            result = Optional.of(file.getAbsolutePath());
        }
        return result;
    }

    private boolean maskMatches(String src, String mask) {
        StringBuilder regex = new StringBuilder();
        for (int i = 0; i < mask.length(); ++i) {
            final char c = mask.charAt(i);
            switch (c) {
                case '*':
                    regex.append(".*");
                    break;
                case '?':
                    regex.append('.');
                    break;
                case '.':
                    regex.append("\\.");
                    break;
                case '\\':
                    regex.append("\\\\");
                    break;
                default:
                    regex.append(c);
            }
        }
        return src.matches(regex.toString());
    }

    private boolean init(Args a) {
        boolean success = true;
        if (a.directory() == null) {
            System.out.println("Необходимо указать директорию поиска -d");
            success = false;
        } else {
            root = new File(a.directory());
            if (!root.isDirectory()) {
                System.out.println("Указанный путь не является директорией");
                success = false;
            } else {
                if (a.output() == null) {
                    System.out.println("Файл результата -o не указан, будет записан в корень диска C:\\out.txt");
                    output = new File("C:\\out.txt");
                } else {
                    output = new File(a.output());
                }
                if (a.isMask()) {
                    type = MASK;
                } else if (a.isFull()) {
                    type = FULL;
                } else if (a.isRegex()) {
                    type = REGEX;
                } else {
                    System.out.println("Необходимо указать тип поиска -m -f -r");
                    success = false;
                }
                if (a.name() == null) {
                    System.out.println("Укажите, что искать -n");
                    success = false;
                } else {
                    mask = a.name();
                }
            }
        }
        return success;
    }

    public static void main(String[] args) {
        Find finder = new Find();
        //args = new String[] {"-d", "C://Projects", "-f", "-n", "pom.xml", "-o", "C://ooout.txt"};
        //args = new String[] {"-d", "C://Projects", "-r", "-n", ".*.xml.*", "-o", "C://ooout.txt"};
        args = new String[]{"-d", "C://Projects", "-m", "-n", "p*.xml", "-o", "C://ooout.txt"};
        if (finder.init(new Args(args))) {
            finder.find();
        }

    }
}
