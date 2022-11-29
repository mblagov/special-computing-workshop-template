package ru.spbu.apcyb.svp.tasks.task4;

import java.util.concurrent.Callable;

public class TanTask implements Callable<String> {

  private final int index;
  private final long total;
  private final double num;

  TanTask(int index, long total, double num) {
    this.index = index;
    this.total = total;
    this.num = num;
  }

  @Override
  public String call() {
    double tan = Math.tan(num);
    return String.format("[%s/%s]: %s%n", index, total, tan);
  }
}

