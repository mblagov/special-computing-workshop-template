package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class WWFTests {
    WorkWithFiles wwf = new WorkWithFiles();

    @Test
    public void getFilesTest() {
        String dir = "src\\main\\resources\\testDir";
        String saveIn = "src\\main\\resources\\test1";
        String checkFile = "src\\main\\resources\\checkIn.txt";
        wwf.getFilesInDirectory(dir, saveIn);

    }
}
