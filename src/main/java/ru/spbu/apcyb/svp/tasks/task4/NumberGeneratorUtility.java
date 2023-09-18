package ru.spbu.apcyb.svp.tasks.task4;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class NumberGeneratorUtility {

  private NumberGeneratorUtility() {

  }

  public static void generateSequence(String filename, int countInLine, int countOfLines)
      throws IOException {
    try (var fileWriter = new FileWriter(filename)) {
      var random = new Random(System.currentTimeMillis());
      for (int i = 0; i < countOfLines; i++) {
        for (int j = 0; j < countInLine; j++) {
          fileWriter.write(random.nextDouble() + " ");
        }
        fileWriter.write("\n");
      }
      fileWriter.flush();
    }
  }
}
