package ru.job4j.io;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Predicate;

public class Search {
    List<File> files(String parent, List<String> exts) {
        Predicate<File> p = (file) -> !file.isDirectory() && exts.contains(FilenameUtils.getExtension(file.getName()));
        return this.files(parent, p);
    }

    List<File> files(String parent, Predicate<File> p) {
        List<File> result = new ArrayList<>();
        Queue<File> files = new LinkedList<>();
        files.offer(new File(parent));
        while (!files.isEmpty()) {
            File f = files.poll();
            if (p.test(f)) {
                result.add(f);
            }
            File[] children = f.listFiles();
            if (!(children == null)) {
                for (File child : f.listFiles()) {
                    files.offer(child);
                }
            }
        }
        return result;
    }

    public static void createTestFiles(Path[] testfiles) {
        for (Path f : testfiles) {
            try {
                Files.createDirectories(f.getParent());
                if (!Files.exists(f)) {
                    Files.createFile(f);
                }
            } catch (IOException e) {
                System.err.println("already exists: " + e.getMessage());
            }
        }
    }
}
