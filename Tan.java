package ru.spbu.apcyb.svp.tasks;

import java.util.concurrent.Callable;

public class Tan implements Callable<Double> {
  private final double data;

  Tan(double data) {
    this.data = data;
  }

  @Override
  public Double call() throws Exception {
    return Math.tan(this.data);
  }
}
