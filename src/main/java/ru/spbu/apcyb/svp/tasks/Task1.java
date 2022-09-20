package ru.spbu.apcyb.svp.tasks;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;


/**
 * Задание 1.
 */


public class Task1 {

  private int sum;
  private int[] values;
  private final java.util.logging.Logger logger =
      java.util.logging.Logger.getLogger(Task1.class.getName());


  private int ans = 0;


  public int getSum() {
    return this.sum;
  }

  public int[] getValues() {
    return this.values;
  }

  public Logger getLogger() {
    return this.logger;
  }

  public void setSum(int sum) {
    this.sum = sum;
  }

  public int getAns() {
    return this.ans;
  }

  public void setValues(int[] values) {
    this.values = values;
  }


  protected void loggerConfiguration() {
    try {
      OutputStream fileOutputStream = new FileOutputStream("output.log");
      PrintStream outputStream = new PrintStream(fileOutputStream);
      SimpleFormatter simpleFormatter = new SimpleFormatter();
      StreamHandler streamHandler = new StreamHandler(outputStream, simpleFormatter);
      this.logger.setUseParentHandlers(false);
      this.logger.addHandler(streamHandler);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }


  }


  protected String readString() {
    InputStream inputStream = System.in;
    Reader inputStreamReader = new InputStreamReader(inputStream);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    String data;
    try {
      data = bufferedReader.readLine();
    } catch (java.io.IOException e) {
      this.logger.log(Level.INFO, "Error with input, cant read");
      e.printStackTrace();
      return null;
    }
    return data;
  }


  protected void parseString(String input) throws NumberFormatException, ArithmeticException {
    String[] inputFormat = input.trim().replaceAll("\\s{2,}", " ").split(" ");
    int num;
    this.values = new int[inputFormat.length - 1];
    try {
      num = Integer.parseInt(inputFormat[0]);
      if (num < 0) {
        throw new ArithmeticException("invalid sum");
      }
      this.sum = num;
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Input error");
    }
    for (int i = 1; i < inputFormat.length; i++) {
      try {
        this.values[i - 1] = Integer.parseInt(inputFormat[i]);
        if (this.values[i - 1] < 0) {
          throw new ArithmeticException("invalid value");
        }

      } catch (NumberFormatException e) {
        throw new NumberFormatException("Input error");
      }

    }
    Arrays.sort(this.values);
  }


  protected void printCombination(java.util.logging.Logger logger, List<Integer> combination) {
    String out = combination.toString();
    logger.log(java.util.logging.Level.INFO, out);
  }

  protected void printCombination(java.util.logging.Logger logger, int ans) {
    String out = Integer.toString(ans);
    logger.log(java.util.logging.Level.INFO, out);
  }

  protected List<List<Integer>> getNumsOfCombinations(int currentSum, int maxIndex,
      int[] values) {
    List<List<Integer>> result = new ArrayList<>();
    if (currentSum == 0) {
      result.add(new ArrayList<>());
    } else {
      for (int i = maxIndex; i >= 0; i--) {
        int currentValue = values[i];
        if (currentValue > currentSum) {
          continue;
        }
        for (List<Integer> remain : getNumsOfCombinations(currentSum - currentValue, i,
            values)) {
          List<Integer> currentCombination = new ArrayList<>();
          currentCombination.add(currentValue);
          currentCombination.addAll(remain);
          if (currentSum == this.sum) {
            this.ans += 1;

          } else {
            result.add(currentCombination);
          }
        }
      }
    }
    return result;
  }

  protected List<List<Integer>> getCombinations(int currentSum, int maxIndex, int[] values
  ) {
    List<List<Integer>> result = new ArrayList<>();
    if (currentSum == 0) {
      result.add(new ArrayList<>());
    } else {
      for (int i = maxIndex; i >= 0; i--) {
        int currentValue = values[i];
        if (currentValue > currentSum) {
          continue;
        }
        for (List<Integer> remain : getCombinations(currentSum - currentValue, i,
            values)) {
          List<Integer> currentCombination = new ArrayList<>();
          currentCombination.add(currentValue);
          currentCombination.addAll(remain);
          if (currentSum == this.sum) {
            printCombination(this.logger, currentCombination);

          } else {
            result.add(currentCombination);
          }
        }
      }
    }
    return result;
  }


  public static void main(String[] args) {

    Task1 task = new Task1();
    String input = task.readString();
    task.parseString(input);

    task.getNumsOfCombinations(task.sum, task.values.length - 1, task.values);
    task.printCombination(task.logger, task.ans);
    task.getCombinations(task.sum, task.values.length - 1, task.values);


  }

}
