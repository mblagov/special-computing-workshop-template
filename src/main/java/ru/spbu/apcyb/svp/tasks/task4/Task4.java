package ru.spbu.apcyb.svp.tasks.task4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Задание 4.
 */
public class Task4 {

  private static volatile int cnt;

  private static long getRuntime(Runnable method) {
    long time = System.nanoTime();
    method.run();
    time = System.nanoTime() - time;
    return TimeUnit.NANOSECONDS.convert(time, TimeUnit.NANOSECONDS);
  }

  public static void singeThreadedTan(List<Double> nums) throws IOException {
    String outPath = "src/main/java/ru/spbu/apcyb/svp/tasks/task4/STOUT.txt";
    try (FileWriter out = new FileWriter(outPath)) {
      for (int i = 0; i < nums.size(); i++) {
        double tan = Math.tan(nums.get(i));
        out.write(String.format("[%s/%s] = %s%n", i, nums.size(), tan));
      }
    }
  }

  public static void main(String[] args) throws IOException {
    String numsPath = "src/main/resources/nums.txt";
    String outPath1 = "src/main/java/ru/spbu/apcyb/svp/tasks/task4/STOUT.txt";
    String outPath2 = "src/main/java/ru/spbu/apcyb/svp/tasks/task4/MTOUT.txt";
    ArrayList<Double> nums = new ArrayList<>();
    try (Scanner scanner = new Scanner(new File(numsPath))) {
      scanner.useDelimiter(" ");
      while (scanner.hasNext()) {
        nums.add(Double.parseDouble(scanner.next()));
      }
    }
    int n = 1000;
    long time1 = getRuntime(() -> {
      try {
        singeThreadedTan(nums.subList(0, n));
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
//    CopyOnWriteArrayList<String> outMT = new CopyOnWriteArrayList<>();
//    long time2 = getRuntime(() -> multiThreadedTan(outMT, nums.subList(0, n)));

//    try (FileWriter out = new FileWriter(outPath2)) {
//      for (String line : outMT) {
//        out.write(line);
//      }
//    }

    System.out.println("Single-Threaded Time: " + time1);
//    System.out.println("Multi-Threaded Time: " + time2);
  }
}
