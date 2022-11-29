package ru.spbu.apcyb.svp.tasks.task4;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Задание 4.
 */
public class Task4 {

  private static long timed(Runnable function) {
    long start = System.nanoTime();
    function.run();
    long time = System.nanoTime() - start;
    return TimeUnit.NANOSECONDS.convert(time, TimeUnit.NANOSECONDS);
  }

  public static void main(String[] args) {
    String numsPath = "src/main/resources/nums.txt";
    String outPath1 = "src/main/java/ru/spbu/apcyb/svp/tasks/task4/SingleThreadedTanOutput.txt";
    String outPath2 = "src/main/java/ru/spbu/apcyb/svp/tasks/task4/MultiThreadedTanOutput.txt";

    int numberOfValuesToProcess = 100000;

    SingleThreadedTan singleThreadedTan = new SingleThreadedTan(numsPath, outPath1,
        numberOfValuesToProcess);

    MultiThreadedTan multiThreadedTan = new MultiThreadedTan(numsPath, outPath2,
        numberOfValuesToProcess);

    System.out.println("Single-Threaded Time: " + timed(singleThreadedTan));
    System.out.println("Multi-Threaded Time: " + timed(multiThreadedTan));
  }
}
