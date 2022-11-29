package ru.spbu.apcyb.svp.tasks.task4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SingleThreadedTan implements Runnable{

  private String numsPath;
  private String outPath;
  private long total;

  SingleThreadedTan(String numsPath, String outPath, int total) {
    this.numsPath = numsPath;
    this.outPath = outPath;
    this.total = total;
  }

  @Override
  public void run() {
    try (FileWriter out = new FileWriter(this.outPath)) {
      try (Scanner scanner = new Scanner(new File(this.numsPath))) {
        scanner.useDelimiter("\n");
        int cnt = 0;
        while (scanner.hasNext()) {
          cnt++;
          String[] pairIntDouble = scanner.next().split(" ");
          int index = Integer.parseInt(pairIntDouble[0]);
          if (index > this.total) {
            break;
          }
          double num = Double.parseDouble(pairIntDouble[1]);
          double tan = Math.tan(num);
          out.write(String.format("[%s/%s]: %s%n", cnt, this.total, tan));
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
