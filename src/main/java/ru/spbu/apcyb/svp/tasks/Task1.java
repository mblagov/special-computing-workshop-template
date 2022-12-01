package ru.spbu.apcyb.svp.tasks;

import java.util.Arrays;
import java.util.Scanner;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Задание 1.
 */
public class Task1 {
  private static final Logger logger = LogManager.getLogger(Task1.class);
  
  /**
  * Запускает программу.
  *
  * @param args вспопомагетельная переменная.
  */
  public static void main(String[] args) {
    
    //Число для размена
    long numb;
    //Номиналы, на которые надо разменять
    long[] val;
  
    logger.log(Level.INFO, "Ввод данных!\nВведите число для размена: ");
  
    try {
      numb = readNumb();
    
      logger.log(Level.INFO, "Введите номиналы, на которые надо разменять через пробел: ");
      val = transformVal(numb, readVal());
    
      if (exchange(numb, numb, val, new long[val.length], 0, 0) == 0) {
        logger.log(Level.INFO, "Разменов нет!");
      }
    } catch (Exception e) {
      logger.log(Level.INFO, e.getMessage());
    }
  }
  
  
  /**
   * Читает и проверяет число для размена.
   *
   * @return проверенное число для разменов
   */
  public static long readNumb() {
  
    Scanner scanner = new Scanner(System.in);
    String str = scanner.nextLine();
    long numb;
    
    try {
      numb = Long.parseLong(str);
    } catch (Exception e) {
      throw new RuntimeException("Ошибка ввода! Неправильно введено число для разменов!");
    }
  
    if (numb <= 0) {
      throw new RuntimeException("Ошибка ввода! Число для размена должна быть больше нуля!");
    }
    
    return numb;
  }
  
  /**
  * Метод преобразования строки в массив чисел.
  *
  * @return массив чисел из строчки.
  */
  public static long[] readVal() {
  
    Scanner scanner = new Scanner(System.in);
    String str = scanner.nextLine();
    
    if (str.matches("(\\d+\\s*)+")) {
      String[] noms = str.split(" ");

      long[] val = new long[noms.length];

      for (int i = 0; i < val.length; i++) {
        val[i] = Long.parseLong(noms[i]);
      }

      return val;
    }
    
    throw new RuntimeException("Ошибка ввода! Неправильно введена строка номиналов!");
  }
  
  /**
  * Преобразования массива номиналов: уборка номиналов, которые больше числа и уборка повторений.
  *
  * @param numb число, больше которого не должны быть номиналы
  * @param val  массив номиналов, который нужно по необходимости преобразовать
  * @return преобразованный массив готовый к размену
  */
  public static long[] transformVal(long numb, long[] val) {
    
    //Сортировка по возрастанию
    Arrays.sort(val);
  
    //Проверка на неотрицательность
    if (val[0] <= 0) {
      throw new RuntimeException("Ошибка ввода! Номинал не может быть меньше или равен 0!");
    }
  
    //Проверка, что существуют номиналы меньше числа
    if (numb < val[0]) {
      throw new RuntimeException("Разменов нет! Все номиналы больше числа для размена!");
    }
    
    //Убираем повторы
    val = Arrays.stream(val).distinct().toArray();
    
    //Уборка номинало, которые больше числа
    for (int i = 1; i < val.length; i++) {
      if (numb < val[i]) {
        val = cutMassive(val, i);
        break;
      }
    }
    
    return val;
  }

  /**
  * Укорачивание массива.
  *
  * @param mas массив, с которым идет работа
  * @param n   новый размер массива
  * @return укороченный массив
  */
  public static long[] cutMassive(long[] mas, int n) {
    
    //укороченный массив
    long[] helper = new long[n];

    System.arraycopy(mas, 0, helper, 0, n);

    return helper;
  }

  /**
  * Метод нахождения разложения рекурсией.
  *
  * @param constNumb число для размена
  * @param numb      число для размена после вычитания нужно количества номиналов
  * @param val       массив номиналов, на которые размениваем
  * @param count     массив количества использований каждого номинала
  * @param h    индекс, предотвращающий появления результатов с точностью до перестановки
  * @param n         номер размена
  */
  public static long exchange(long constNumb, long numb, long[] val, long[] count, int h, long n) {
    
    long div = constNumb / val[h];
    //Количество полных вхождений в число размена

    for (int i = 0; i <= div; i++) {
      if (numb >= 0) {
        count[h] = i;

        if (numb == 0) {
          n++;

          output(val, count, n);
        } else if (h + 1 < val.length) {
          n = exchange(constNumb, numb, val, count, h + 1, n);
        }
      } else {
        return n;
      }

      numb -= val[h];
    }

    return n;
  }

  /**
  * Метод для вывода всех разменов.
  *
  * @param val   массив номиналов, на которые размениваем
  * @param count массив количеств использований номиналов
  * @param n     номер размена
  */
  private static void output(long[] val, long[] count, long n) {
    
    if (n == 1) {
      logger.log(Level.INFO, "Возможный(ые) размен(ы) ((n) - номинал):\n");
    }
    
    logger.log(Level.INFO, "{} размен - [ ", n);

    for (int i = 0; i < count.length; i++) {
      if (count[i] != 0) {
        logger.log(Level.INFO, "{} x ({}) ", count[i], val[i]);
      }
    }
    
    logger.log(Level.INFO, "]\n");
  }
}
