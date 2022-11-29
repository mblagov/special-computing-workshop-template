package ru.spbu.apcyb.svp.tasks.task4;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface Computable {
  void compute() throws IOException, ExecutionException;
}
