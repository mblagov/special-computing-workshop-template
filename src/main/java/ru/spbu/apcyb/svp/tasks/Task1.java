package ru.spbu.apcyb.svp.tasks;

import java.util.Scanner;

/**
 * Start point class.
 */

public class Task1 {

  /**
   * Start point method.
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the amount of change: ");
    long change = scanner.nextLong();
    if (change < 0) {
      throw new ArithmeticException("Negative change!");
    }

    int numDenominators = scanner.nextInt();
    System.out.println("Input number of denominators: ");
    if (numDenominators < 0) {
      throw new ArithmeticException("Negative numberOfDen");
    }
    long[] denominators = getDenominatorsFromUser(scanner, numDenominators);

    Atm atm = new Atm();
    long numCombs = atm.getChangeByDenominators(change, denominators);
    System.out.println(numCombs);

    scanner.close();
  }

  private static long[] getDenominatorsFromUser(Scanner scanner, int numDenominators) {
    long[] denominators = new long[numDenominators];
    for (int i = 0; i < numDenominators; i++) {
      System.out.print("Enter denominator " + (i + 1) + ": ");
      denominators[i] = scanner.nextLong();
      if (denominators[i] < 0) {
        throw new ArithmeticException("Negative denominator!");
      }
    }
    return denominators;
  }
}


