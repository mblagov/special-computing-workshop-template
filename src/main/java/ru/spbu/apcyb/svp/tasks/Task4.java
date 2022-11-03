package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Задание 4.
 */
public class Task4 {

  public static void main(String[] args)  throws FileNotFoundException {
    String numsPath = "src/main/resources/nums.txt";
    ArrayList<Double> nums = new ArrayList<>();
    try (Scanner scanner = new Scanner(new File(numsPath))) {
      scanner.useDelimiter(" ");
      while (scanner.hasNext()) {
        nums.add(Double.parseDouble(scanner.next()));
      }
    }
    for (Double num: nums) {
      System.out.println(num);
    }
  }
}
