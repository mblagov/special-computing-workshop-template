package ru.spbu.apcyb.svp.tasks.task4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SingleThreadedTan implements Computable {

  private final String numsPath;
  private final String outPath;
  private final long total;

  public SingleThreadedTan(String numsPath, String outPath, long total) {
    this.numsPath = numsPath;
    this.outPath = outPath;
    this.total = total;
  }

  @Override
  public void compute() {
    try (FileWriter out = new FileWriter(this.outPath)) {
      try (Scanner scanner = new Scanner(new File(this.numsPath))) {
        scanner.useDelimiter("\n");
        int idx = 1;
        while (scanner.hasNext()) {
          String[] pairIntDouble = scanner.next().split(" ");
          int index = Integer.parseInt(pairIntDouble[0]);
          if (index > this.total) {
            break;
          }
          double num = Double.parseDouble(pairIntDouble[1]);
          double tan = Math.tan(num);
          out.write(String.format("[%s/%s]: %s%n", idx++, this.total, tan));
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
