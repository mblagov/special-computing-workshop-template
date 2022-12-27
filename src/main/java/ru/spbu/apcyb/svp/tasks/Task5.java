package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Задание 5.
 */
public class Task5 {
  
  /** Cамый обычный main.
   *
   * @param args - самый обычный args
   */
  public static void main(String[] args) throws Exception {
    start();
  }
  
  /** Метод запуска программы.
   *
   */
  public static void start() throws Exception {
    
    try {
      Map<String, Long> stream = readFile("c://ВиМ.txt");
    
      toCountFile(stream);
      //Лучше это не запускать. (* ^ ω ^)
      //Если потом не хочется ждать ответа Идеи, а после удалять все txt файлы.
      //Но она работает, честно, но мне не понравилось чистить компьютер от 34тыс txt файлов.
      //toNameFile(stream);
    } catch (Exception e) {
      throw new Exception(e + ", попробуйте снова!");
    }
  }
  
  /** Метод чтения файла c Java Stream, подсчета вхождений каждого слова и запуска записей в файлы.
   *
   * @param fileName - путь к файлу
   * @return стрим, содержащий слова и их количество
   */
  public static Map<String, Long> readFile(String fileName) throws FileNotFoundException {
    
    try {
      return Files.lines(Paths.get(fileName), Charset.forName("windows-1251"))
          // Разделение по знакам пунктуации, пробелу, цифре или одиночной букве
          .flatMap(l -> Stream.of(l.split("[\\p{Punct}«»–…—„“ }\\s\\d\\w{1}]")))
          //уборка пустых
          .filter(item -> !item.equals(""))
          //группировка
          .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    
    } catch (IOException e) {
      throw new FileNotFoundException("Файл не был найден!");
    }
  }
  
  /** Вывод каждого слова с его количеством в файле counts.txt.
   *
   * @param stream - стрим, содержащий слова и их количество
   * @throws IOException если проблема с файлом или FileWriter
   */
  public static void toCountFile(Map<String, Long> stream) throws IOException {
    
    File file = new File("counts.txt");
    FileOutputStream out;
    
    //Если файл существует, то он его очищает
    try {
      if (file.createNewFile()) {
        out = new FileOutputStream(file, false);
      } else {
        out = new FileOutputStream(file);
      }
  
      StringBuilder data = new StringBuilder();
      stream.forEach((word, count) -> data.append(word)
          .append(" - ")
          .append(count.toString())
          .append("\n"));
      out.write(data.toString().getBytes());
      out.close();
      
    } catch (Exception e) {
      throw new FileSystemException("Невозможно создать counts.txt!");
    }
    
  }
  
  /** При помощи CompletableFuture записает слова в разные файлы.
   *
   * @param stream - стрим, содержащий слова и их количество
   * @throws RuntimeException если не хватило терпения (─‿‿─)
   */
  public static void toNameFile(Map<String, Long> stream) {
    
    ThreadPool threadPool = new ThreadPool(10);
  
    try {
      
      stream.forEach((word, count) ->  CompletableFuture.supplyAsync(
          () -> {
        
          try {
            File file = new File(word + ".txt");
            FileOutputStream out;
          
            //Если файл существует, то он его очищает
            if (file.createNewFile()) {
              out = new FileOutputStream(file, false);
            } else {
              out = new FileOutputStream(file);
            }
          
            StringBuilder data = new StringBuilder();
          
            for (int i = 0; i < count; i++) {
              data.append(word)
                  .append(" ");
            }
          
            out.write(data.toString().getBytes());
            out.close();
          
            return true;
          
          
          } catch (Exception e) {
            throw new RuntimeException(e + "Невозможно выполнить запрос!");
          }
        },
      
          threadPool
      ));
    } finally {
      threadPool.shutdown();
    }
  }
}

