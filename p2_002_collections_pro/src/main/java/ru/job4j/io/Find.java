package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.function.Predicate;

public class Find {
    private File root;
    private File output;

    public void find(Predicate<File> p) {
        List<File> files = new Search().files(this.root.toString(), p);
        try (PrintWriter result = new PrintWriter(new FileOutputStream(this.output))) {
            for (File f : files) {
                result.println(f.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private Predicate<File> init(Args a) {
        Predicate<File> p = file -> false;
        if (a.directory() == null) {
            System.out.println("Необходимо указать директорию поиска -d");
        } else {
            this.root = new File(a.directory());
            if (!this.root.isDirectory()) {
                System.out.println("Указанный путь не является директорией");
            } else {
                if (a.output() == null) {
                    System.out.println("Файл результата -o не указан, будет записан в корень диска C:\\out.txt");
                    this.output = new File("C:\\out.txt");
                } else {
                    this.output = new File(a.output());
                }
                if (a.name() == null) {
                    System.out.println("Укажите, что искать -n");
                } else {
                    String mask = a.name();
                    if (a.isMask()) {
                        p = file -> this.maskMatches(file.getName(), mask);
                    } else if (a.isFull()) {
                        p = file -> mask.equals(file.getName());
                    } else if (a.isRegex()) {
                        p = file -> file.getName().matches(mask);
                    } else {
                        System.out.println("Необходимо указать тип поиска -m -f -r");
                    }
                }
            }
        }
        return p;
    }

    public static void main(String[] args) {
        Find finder = new Find();
        //args = new String[] {"-d", "C://Projects", "-f", "-n", "pom.xml", "-o", "C://ooout.txt"};
        //args = new String[] {"-d", "C://Projects", "-r", "-n", ".*.xml.*", "-o", "C://ooout.txt"};
        args = new String[]{"-d", "C://Projects", "-m", "-n", "p*.java", "-o", "C://ooout.txt"};
        finder.find(finder.init(new Args(args)));


    }
}
