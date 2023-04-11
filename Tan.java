package ru.spbu.apcyb.svp.tasks;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Tan extends Thread {

  FileWriter outMulti;
  private final int startLine;
  private final int lines;
  private final String input;

  /**
   * Конструктор.
   *
   * @param startLine первая линия для чтения из файла.
   * @param lines     количество линий для чтения.
   * @param outMulti  файл для вывода.
   * @param input     файл для чтения значений в формате строки.
   */
  public Tan(int startLine, int lines, FileWriter outMulti, String input) {
    this.startLine = startLine;
    this.lines = lines;
    this.outMulti = outMulti;
    this.input = input;
  }
  public void tanToFile() throws FileNotFoundException {
    try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
      String line = "";
      for (int i = 0; i < startLine; i++) {
        line = reader.readLine();

      }
      outMulti.write(Math.tan(Double.parseDouble(line)) + "\n");
      for (int j = 1; j < lines; j++) {

        line = reader.readLine();

        outMulti.write(Math.tan(Double.parseDouble(line)) + "\n");
      }
    } catch (IOException e) {
      throw new FileNotFoundException("Ошибка чтения из файла в многопоточном режиме");
    }
  }
  /**
   * Исполняемая команда.
   */
  @Override
  public void run() {
    try {
      tanToFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}