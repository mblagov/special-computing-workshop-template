package ru.spbu.apcyb.svp.tasks.multithreading;


import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;


public class DataWriter {
  private final Path pathFile;

  public DataWriter(Path path) {
    this.pathFile = path;
  }

  public void writeRandomToFile() throws IOException {
    if (Files.isDirectory(pathFile)) {
      throw new IOException("Not file.");
    }
    double[] data = new double[10000];
    for (int i = 0; i < 10000; i++) {
      data[i] = Math.random() * 100;
    }
    List<String> dataList = Arrays.stream(data).mapToObj(String::valueOf).toList();
    Files.write(pathFile, dataList, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
  }

  public void writeArrayToFile(double[] dataArray) throws IOException {
    if (Files.isDirectory(pathFile)) {
      throw new IOException("Not file.");
    }
    List<String> dataList = Arrays.stream(dataArray).mapToObj(String::valueOf).toList();
    Files.write(pathFile, dataList, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
  }

  public void writeListToFile(List<Double> dataList) throws IOException {
    try (FileWriter writer = new FileWriter(String.valueOf(pathFile))) {
      for (Double listElem : dataList) {
        writer.write(String.valueOf(listElem) + "\n");
      }
    } catch (IOException ex) {
      throw new IOException("Not file.");
    }
  }
}
