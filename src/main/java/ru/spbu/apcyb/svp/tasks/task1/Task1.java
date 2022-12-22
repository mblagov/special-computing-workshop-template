package ru.spbu.apcyb.svp.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.aether.spi.log.LoggerFactory;

/**
 * Задание 1.
 */
public class Task1 {

  /**
   * main method.
   *
   * @param args
   *
   */
  public static void main(String[] args) {
    var sc = new Scanner(System.in);
    var input = sc.nextLine();
    var parsedInput = parseInput(input);
    var sum = parsedInput.get(0);
    var banknotes = parsedInput;
    banknotes.remove(0);

    var logger = Logger.getLogger(Task1.class.getName());
    logger.log(Level.INFO, sum.toString());
    logger.log(Level.INFO, banknotes.toString());
  }

  /**
   *
   * @param input
   * @return
   * returns the set of available banknotes
   */
  private static List<Integer> parseInput(String input) {
    int[] banknotes;
    try {
      banknotes = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
    } catch (NumberFormatException exception) {
      throw new NumberFormatException("Not a number");
    }

    var set = new HashSet<Integer>(banknotes.length);
    for(var banknote : banknotes) {
      if(banknote > 0)
        set.add(banknote);
      else
        throw new ArithmeticException("Provided a non-positive banknote value or sum value");
    }
    if(set.size() < 2) throw new InputMismatchException("Not enough values in the input");

    var ans = new ArrayList<>(set);
    return ans;
  }
}
