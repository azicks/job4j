package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;

public class AnalizyTest {

    @Test
    public void whenUnavailable() {
        String[] data = new String[3];
        String src = "./data/server.status";
        String dst = "./data/unavailable.csv";
        new Analizy().unavailable(src, dst);
        try (BufferedReader reader = new BufferedReader(new FileReader(dst))) {
            data[0] = reader.readLine();
            data[1] = reader.readLine();
            data[2] = reader.readLine();
        } catch (
                Exception e) {
            e.printStackTrace();
        }

        assertEquals(data[0], "10:57:01;10:59:01");
        assertEquals(data[1], "11:01:02;11:02:02");
        assertNull(data[2]);

    }
}