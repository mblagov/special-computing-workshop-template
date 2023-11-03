package ru.spbu.apcyb.svp.tasks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Задание 4. */
public class Task4 {
  public static final String INPUT = "numbers.txt";
  public static final Logger logger = Logger.getLogger(Task4.class.getName());

  private final double[] buffer;
  private int numThreads;

  Task4(int numThreads) {
    this.numThreads = numThreads;
    this.buffer = new double[numThreads];
  }

  public double[] getBuffer() {
    return buffer;
  }

  public static boolean generateNums(int count, String inputAddress) throws IOException {

    Random rand;
    try {
      rand = SecureRandom.getInstanceStrong();

    } catch (Exception e) {
      throw new IOException("Ошибка генерации последовательности чисел");
    }
    try (FileWriter fw = new FileWriter(inputAddress, false)) {
      for (int i = 0; i < count; i++) {
        double randNum = rand.nextDouble() * 73 - 37;
        fw.write(randNum + "\n");
      }
      return true;
    } catch (IOException e) {
      throw new IOException("Ошибка записи чисел в файл");
    }
  }

  public static long tanSingle(int count, FileWriter fw, String inputAddress)
      throws FileNotFoundException {
    long start = System.nanoTime();
    try (BufferedReader br = new BufferedReader(new FileReader(inputAddress))) {
      String line;
      long l = 0;
      while ((line = br.readLine()) != null && l < count) {
        fw.write(String.valueOf(Math.tan(Double.parseDouble(line))));
        fw.append("\n");
        l++;
      }
    } catch (Exception e) {
      throw new FileNotFoundException("Ошибка чтения файла в однопоточном режиме");
    }
    return System.nanoTime() - start;
  }

  protected void writeToFile(FileWriter fileWriter, Future<Double>[] outBuffer)
      throws IOException, ExecutionException, InterruptedException {
    for (int i = 0; i < this.numThreads; i++) {
      fileWriter.write(outBuffer[i].get().toString().concat("\n"));
    }
  }

  protected void readToBuffer(BufferedReader br) throws IOException {
    int count = 0;
    String line;
    while (count != 10 && (line = br.readLine()) != null) {
      this.buffer[count] = Double.parseDouble(line);
      count++;
    }
    if (count != 10) {
      this.numThreads = count;
    }
  }

  protected void submitTasks(FileWriter fw, BufferedReader br)
      throws ExecutionException, InterruptedException, IOException {
    Future<Double>[] outBuffer = new Future[this.numThreads];
    ExecutorService executorService = Executors.newFixedThreadPool(this.numThreads);
    while (this.numThreads != 0) {
      for (int i = 0; i < this.numThreads; i++) {
        Tan tan = new Tan(this.buffer[i]);
        Future<Double> value = executorService.submit(tan);
        outBuffer[i] = value;
      }

      writeToFile(fw, outBuffer);
      readToBuffer(br);
    }
    executorService.shutdown();
  }

  protected long tanThreaded(String in, String out) throws IOException {

    File file = new File(in);
    File output = new File(out);
    long start = System.nanoTime();
    try (FileWriter fw = new FileWriter(output);
        BufferedReader br = new BufferedReader(new FileReader(file))) {
      readToBuffer(br);

      submitTasks(fw, br);

    } catch (ExecutionException | InterruptedException e) {
      Thread.currentThread().interrupt();
      logger.log(Level.SEVERE, "Ошибка потока");
    } catch (IOException e) {
      throw new IOException("Ошибка входного или выходного файла");
    }
    return System.nanoTime() - start;
  }

  public static void main(String[] args) throws IOException {
    int numLines = 100000;
    int numFlows = 10;

    generateNums(numLines, INPUT);
    long singleTime;
    try (FileWriter outSingle = new FileWriter("Single.txt", false)) {
      singleTime = tanSingle(numLines, outSingle, INPUT);
    } catch (IOException e) {
      throw new IOException("Ошибка с файлом для вывода в однопоточном режиме");
    }
    Task4 task = new Task4(numFlows);

    long multiTime = task.tanThreaded(INPUT, "Multi.txt");
    String str =
        "Количество строк = "
            + numLines
            + "\n"
            + "Количество потоков = "
            + numFlows
            + "\n"
            + "Однопоточное время = "
            + singleTime
            + " нс\n"
            + "Многопоточное время = "
            + multiTime
            + " нс\n";
    logger.log(Level.INFO, str);
  }
}
