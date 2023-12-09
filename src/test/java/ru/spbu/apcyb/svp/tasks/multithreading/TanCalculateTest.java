package ru.spbu.apcyb.svp.tasks.multithreading;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TanCalculateTest {
  @Test
  void testSimple() throws IOException, InterruptedException {
    Path fileRead = Path.of("src/test/resources/multithreading/read.txt");
    Path fileWrite = Path.of("src/test/resources/multithreading/write.txt");
    Path fileCheck = Path.of("src/test/resources/multithreading/result_test.txt");
    int numberOfThreads = 9;
    List<Double> dataList = new DataReader(fileRead).readFile();
    List<Double> resultList = new TanCalculate(numberOfThreads).calculateTan(dataList);
    DataWriter dataWriter = new DataWriter(fileWrite);
    dataWriter.writeListToFile(resultList);


    Assertions.assertEquals(-1, Files.mismatch(fileWrite, fileCheck));


//  RandomAccessFile randomAccessFile1 = new RandomAccessFile(fileWrite.toFile(), "r");
//  RandomAccessFile randomAccessFile2 = new RandomAccessFile(fileCheck.toFile(), "r");
//
//      FileChannel ch1 = randomAccessFile1.getChannel();
//      FileChannel ch2 = randomAccessFile2.getChannel();
//
//      long size = ch1.size();
//      MappedByteBuffer m1 = ch1.map(FileChannel.MapMode.READ_ONLY, 0L, size);
//      MappedByteBuffer m2 = ch2.map(FileChannel.MapMode.READ_ONLY, 0L, size);
//      Assertions.assertEquals(m1, m2);



  }
}
