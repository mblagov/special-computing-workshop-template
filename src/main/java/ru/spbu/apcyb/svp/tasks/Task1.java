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
  public static long[] order(long[] arr)
  {
    int i,j,k=0;
    long[] temp = new long[arr.length];
    for (i = 0; i < arr.length; i++)
    {
      for (j = 0; j < k; j++)
      {
        if (arr[i] == temp[j])
          break;
      }
      if (j == k)
      {
        temp[k] = arr[i];
        k++;
      }
    }
    long[] arrayWithoutDupclicate = Arrays.copyOfRange(temp, 0, k);
    Arrays.sort(arrayWithoutDupclicate);
    return arrayWithoutDupclicate;
  }
  public static long[] deleteUselessElements(long[] arr, long limit)
  {
    int i, j;
    for(i=0; i<arr.length;i++)
    {
      if(arr[i]>0)
        break;
    }
    for(j=arr.length-1;j>0;j--)
    {
      if(arr[j] <= limit)
        break;
    }
    return Arrays.copyOfRange(arr, i, j+1);
  }
  public static void print(long[] coins, long[] numberOfUses, long num)
  {
    System.out.print("Размен №"+num+"= [");
    for(int i=0; i<numberOfUses.length; i++)
      if(numberOfUses[i]!=0)
        System.out.print(numberOfUses[i]+"x("+coins[i]+")");
    System.out.println("];");
  }
  public static void main(String[] args)
  {
    long Sum = enterSum();
    long AmountOfCoins = enterAmount();
    long [] coins = enterCoins(AmountOfCoins);
    coins = order(coins);
    coins = deleteUselessElements(coins, Sum);
  }
}
