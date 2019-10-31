package ru.job4j.io;

import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SearchTest {

    @Test
    public void whenCreatingThreeFilesThenSizeOfresultIsThree() {
        String path = System.getProperty("java.io.tmpdir");
        Path[] testfiles = {Paths.get(path + "/foo.bar"),
                Paths.get(path + "/fofofo/foo.bar"),
                Paths.get(path + "/dgfndgnjhbj/lgtbloo.bar")};
        String[] exts = {"bar"};
        Search.createTestFiles(testfiles);
        List<File> result = new Search().files(path, Arrays.asList(exts));
        assertThat(result.size(), is(3));
        assertThat(result.get(0).getName(), is("foo.bar"));
    }
}