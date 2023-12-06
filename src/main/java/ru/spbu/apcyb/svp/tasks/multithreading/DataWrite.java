package ru.spbu.apcyb.svp.tasks.multithreading;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;


public class DataWrite {
  private final double[] data;
  private final Path pathFile;
  public DataWrite(int sizeData, String pathFile) {
    this.data = new double[sizeData];
    this.pathFile = Path.of(pathFile);
  }

  public void writeToFile() throws IOException {
    for (int i = 0; i < data.length; i++) {
      data[i] = Math.random() * 100;
    }
    List<String> dataList = Arrays.stream(data).mapToObj(String::valueOf).toList();
    Files.write(pathFile, dataList, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
  }
}
