package ru.spbu.apcyb.svp.tasks.task5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

public class PrintWords implements Runnable {

  private final String word;
  private final Integer num;
  private final String dir;


  public PrintWords(Integer num, String word, String dir) {
    this.num = num;
    this.word = word;
    this.dir = dir;
  }


  @Override
  public void run() {
    File out = new File(dir, word);
    Integer lowerBound = 100;
    if (num >= lowerBound) {
      try (FileWriter fileWriter = new FileWriter(out)) {
        fileWriter.write(String.join(" ", Collections.nCopies(num, word)));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
