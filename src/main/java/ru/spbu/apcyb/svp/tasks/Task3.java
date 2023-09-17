package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.Objects;

public class Task3 {

  public static boolean listFiles(File dir, Path writeFile, int level) throws IOException {
    if (dir == null || dir.listFiles() == null) {
      return true;
    }
    for (File entry : Objects.requireNonNull(dir.listFiles())) {
      if (entry.isFile()) {
        Files.write(writeFile, Collections.singleton(" ".repeat(level) + "| File: " + entry),
            StandardOpenOption.APPEND);
      } else {
        Files.write(writeFile, Collections.singleton(" ".repeat(level) + "|-> Folder: " + entry),
            StandardOpenOption.APPEND);
        listFiles(entry, writeFile, level + 4);
      }
    }
    return true;
  }

  public static void main(String[] args) {
    File dir = new File(args[0]);
    Path writeFile = Paths.get(args[1]);
    if (!dir.exists() || !dir.isDirectory()) {
      try {
        throw new IOException("There is no such folder: " + dir);
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      try {
        Files.write(writeFile, Collections.singleton("Contents of \"" + dir + "\""));
      } catch (IOException e) {
        e.printStackTrace();
      }
      try {
        listFiles(dir, writeFile, 0);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}