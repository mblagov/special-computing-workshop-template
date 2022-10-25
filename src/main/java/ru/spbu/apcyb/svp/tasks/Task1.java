package ru.spbu.apcyb.svp.tasks;
import java.util.Arrays;
import java.util.Scanner;


/**
 * Задание 1.
 */
public class Task1 {
  public static long check(long number)
  {
    Scanner in = new Scanner(System.in);
    while(number<=0)
    {
      System.out.print("Ошибка; Введите значение > 0: ");
      number = in.nextLong();
    }
    return number;
  }
  public static long enterSum()
  {
    Scanner in = new Scanner(System.in);
    System.out.print("Введите сумму S = ");
    long sum = in.nextLong();
    sum = check(sum);
    return sum;
  }
  public static long enterAmount()
  {
    Scanner in = new Scanner(System.in);
    System.out.print("Введите количество монет n = ");
    long n= in.nextLong();
    n = check(n);
    return n;
  }
  public static long[] enterCoins(long n) {
    int size = (int) n;
    long[] coins = new long[size];
    Scanner in = new Scanner(System.in);
    System.out.println("Введите номиналы: ");
    for (int i = 0; i < size; i++) {
      System.out.print("a[" + i + "] = ");
      coins[i] = in.nextLong();
    }
    return coins;
  }

  public static void main(String[] args)
  {
    long Sum = enterSum();
    long AmountOfCoins = enterAmount();
    long [] coins = enterCoins(AmountOfCoins);
  }
}
