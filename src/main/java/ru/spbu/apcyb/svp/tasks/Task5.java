package ru.spbu.apcyb.svp.tasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Double.parseDouble;

/**
 * Задание 5.
 */
public class Task5 {

  public static void main(String[] args) throws IOException {
    Runnable task = () -> {
      String threadName = Thread.currentThread().getName();
      System.out.println("Hello " + threadName);
    };

    FileWriter fileWriter = new FileWriter("numbers.txt", false);

    for (int i = 0; i < 10000; i++) {
      fileWriter.write(Integer.toString(ThreadLocalRandom.current().nextInt(-1000, 1000)) + " ");
    }
    fileWriter.flush();

    FileReader fileReader = new FileReader("numbers.txt");
    BufferedReader fileBReader = new BufferedReader(fileReader);

    StringBuilder sb = new StringBuilder("").append(fileBReader.readLine());
    String[] tmp = sb.toString().split(" ");
    for (String integer : tmp) {
      System.out.println(Math.tan(parseDouble(integer)));
    }
    fileReader.close();
  }
}
