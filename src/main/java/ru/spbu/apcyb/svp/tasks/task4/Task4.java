package ru.spbu.apcyb.svp.tasks.task4;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Задание 4.
 */
public class Task4 {

  private static long timed(Computable algorithm){
    long start = System.nanoTime();
    try {
      algorithm.compute();
    } catch (IOException e) {
      e.printStackTrace();
    }
    long time = System.nanoTime() - start;
    return TimeUnit.NANOSECONDS.convert(time, TimeUnit.NANOSECONDS);
  }

  public static void main(String[] args) {
    String numsPath = "src/main/resources/nums.txt";
    String outPath1 = "src/main/java/ru/spbu/apcyb/svp/tasks/task4/SingleThreadedTanOutput.txt";
    String outPath2 = "src/main/java/ru/spbu/apcyb/svp/tasks/task4/MultiThreadedTanOutput.txt";

    long total = 100000;

    SingleThreadedTan singleThreadedTan = new SingleThreadedTan(numsPath, outPath1, total);

    MultiThreadedTan multiThreadedTan = new MultiThreadedTan(numsPath, outPath2, total);

    Logger logger = Logger.getLogger(Task4.class.getName());

    logger.info(() -> "Single-Threaded Time: " + timed(singleThreadedTan));
    logger.info(() -> "Multi-Threaded Time: " + timed(multiThreadedTan));
  }
}
