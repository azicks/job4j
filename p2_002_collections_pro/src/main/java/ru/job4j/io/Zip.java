package ru.job4j.io;

import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void pack(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                String entry = FilenameUtils.normalize(source.getPath());
                zip.putNextEntry(new ZipEntry(entry));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static List<File> seekBy(String root, String ext) {
        System.setProperty("user.dir", root);
        List<File> result = new ArrayList<>();
        Queue<File> files = new LinkedList<>();
        files.offer(new File("."));
        while (!files.isEmpty()) {
            File f = files.poll();
            if (!f.isDirectory() && (ext == null || !ext.equals(FilenameUtils.getExtension(f.getName())))) {
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

    public static void main(String[] args) {
        Args a = new Args(args);
        //System.out.println(a.directory());
        //System.out.println(a.exclude());
        //System.out.println(a.output());
        List<File> files = seekBy(a.directory(), a.exclude());
        File dest = new File(a.output());
        new Zip().pack(files, dest);
        //System.out.println(System.getProperty("user.dir"));
    }
}