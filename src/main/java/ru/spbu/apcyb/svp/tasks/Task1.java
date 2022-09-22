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
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;


/**
 * Задание 1.
 */


public class Task1 {

  private long sum;
  private long[] values;
  private final java.util.logging.Logger logger =
      java.util.logging.Logger.getLogger(Task1.class.getName());


  private long ans = 0;


  public long getSum() {
    return this.sum;
  }

  public long[] getValues() {
    return this.values;
  }

  public Logger getLogger() {
    return this.logger;
  }

  public void setSum(long sum) {
    this.sum = sum;
  }

  public long getAns() {
    return this.ans;
  }

  public void setValues(long[] values) {
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
      this.logger.log(Level.INFO, e.toString());
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
      this.logger.log(Level.INFO, e.toString());
      return null;
    }
    return data;
  }


  protected void parseString(String input) throws NumberFormatException, ArithmeticException {
    String[] inputFormat = input.trim().replaceAll("\\s{2,}", " ").split(" ");
    long num;
    Set<Long> tmpValues = new TreeSet<>();
    try {
      num = Long.parseLong(inputFormat[0]);
      if (num <= 0) {
        throw new ArithmeticException("invalid sum");
      }
      this.sum = num;
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Input error");
    }
    for (int i = 1; i < inputFormat.length; i++) {
      try {
        long value = Long.parseLong(inputFormat[i]);
        if (value <= 0) {
          throw new ArithmeticException("invalid value");
        }
        tmpValues.add(value);


      } catch (NumberFormatException e) {
        throw new NumberFormatException("Input error");
      }

    }
    Object[] tmpArray = tmpValues.toArray();
    this.values = new long[tmpArray.length];
    for (int j = 0; j < tmpArray.length; j++) {
      this.values[j] = Long.parseLong(tmpArray[j].toString());
    }
    if (this.values.length == 0) {
      throw new ArithmeticException("invalid values");
    }
  }


  protected void printCombination(java.util.logging.Logger logger, List<Long> combination) {
    String out = combination.toString();
    logger.log(java.util.logging.Level.INFO, out);
  }

  protected void printCombination(java.util.logging.Logger logger, long ans) {
    String out = Long.toString(ans);
    logger.log(java.util.logging.Level.INFO, out);
  }

  protected List<List<Long>> getNumsOfCombinations(long currentSum, int maxIndex,
      long[] values) {
    List<List<Long>> result = new ArrayList<>();
    if (currentSum == 0) {
      result.add(new ArrayList<>());
    } else {
      for (int i = maxIndex; i >= 0; i--) {
        long currentValue = values[i];
        if (currentValue > currentSum) {
          continue;
        }
        for (List<Long> remain : getNumsOfCombinations(currentSum - currentValue, i,
            values)) {
          List<Long> currentCombination = new ArrayList<>();
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

  protected List<List<Long>> getCombinations(long currentSum, int maxIndex, long[] values) {
    List<List<Long>> result = new ArrayList<>();
    if (currentSum == 0) {
      result.add(new ArrayList<>());
    } else {
      for (int i = maxIndex; i >= 0; i--) {
        long currentValue = values[i];
        if (currentValue > currentSum) {
          continue;
        }
        for (List<Long> remain : getCombinations(currentSum - currentValue, i,
            values)) {
          List<Long> currentCombination = new ArrayList<>();
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

  /**
   * На вход принимается строка, состоящая из целых чисел, все числа разделены пробелом.
   * Первое число трактуется как сумма, все последующие - как существующие номиналы.
   * Программа выводит количество комбинаций и сами комбинации.
   *
   * @param args .
   */

  public static void main(String[] args) {

    Task1 task = new Task1();
    String input = task.readString();
    task.parseString(input);

    task.getNumsOfCombinations(task.sum, task.values.length - 1, task.values);
    task.printCombination(task.logger, task.ans);
    task.getCombinations(task.sum, task.values.length - 1, task.values);


  }

}
