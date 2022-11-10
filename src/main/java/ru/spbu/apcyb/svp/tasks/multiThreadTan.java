package ru.spbu.apcyb.svp.tasks;

import java.io.*;
import java.nio.file.Path;

public class multiThreadTan implements Runnable {

    FileWriter multiThreadResFile;
    private int lineNumberToRead;

    public multiThreadTan(int lineNumberToRead, FileWriter multiThreadResFile) {
        this.lineNumberToRead = lineNumberToRead;
        this.multiThreadResFile = multiThreadResFile;
    }

    @Override
    public void run() {
        try {
            resToFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Счиатет тангенс значения в определенной строке из data и выводит в файл результата.
     */
    public void resToFile() throws FileNotFoundException {
        Path filePath = Path.of("data.txt");
        try (BufferedReader readBuffer = new BufferedReader(new FileReader(filePath.toFile()))) {
            String sCurrentLine;
            for (int i = 0; i < lineNumberToRead - 1; i++) {
                sCurrentLine = readBuffer.readLine();
            }
            sCurrentLine = readBuffer.readLine();
            multiThreadResFile.write(String.valueOf(Math.tan(Double.parseDouble(sCurrentLine))) + " ");
            multiThreadResFile.write("\n");
        } catch (IOException e) {
            throw new FileNotFoundException("не нашелся файл data");
        }


    }
}
