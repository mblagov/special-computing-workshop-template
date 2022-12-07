package ru.spbu.apcyb.svp.tasks;

import java.io.*;
import java.nio.file.Path;

public class MultiThread implements Runnable {

    private String fileWriter;
    private int lineNumber;
    private String inputFileName;

    public MultiThread(int lineNumber, String inputFileName, String fileWriterName) {
        this.fileWriter = fileWriterName;
        this.lineNumber = lineNumber;
        this.inputFileName = inputFileName;
    }

    @Override
    public void run() {
        try {
            calculateTanAndWriteToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calculateTanAndWriteToFile() throws IOException {
        Path filePath = Path.of(inputFileName);
        try (BufferedReader readBuffer = new BufferedReader(new FileReader(filePath.toFile())); FileWriter fileWriter1 = new FileWriter(fileWriter, true);) {
            String currentLine;
            for (int i = 0; i < lineNumber - 1; i++) {
                currentLine= readBuffer.readLine();
            }
            currentLine = readBuffer.readLine();
            double value = Math.tan(Double.parseDouble(currentLine));
            writeResultToFile(value, fileWriter1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeResultToFile(double value, FileWriter fileWriter) throws IOException {
        try {
            fileWriter.write((value) + ("\n"));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Файл не найден!");
        } catch (IOException e) {
            throw new IOException("Ошибка при чтении файла или записи в файл!");
        }
    }
}
