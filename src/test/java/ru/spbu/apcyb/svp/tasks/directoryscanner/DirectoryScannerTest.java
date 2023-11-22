package ru.spbu.apcyb.svp.tasks.directoryscanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DirectoryScannerTest {

  @Test
  void pathTest() throws IOException {
    Path pathToScan = Path.of("C:\\Users\\HOME\\IdeaProjects\\special-computing-workshop-templateee\\src\\test\\resources\\read");
    Path pathToSave = Path.of("C:\\Users\\HOME\\IdeaProjects\\special-computing-workshop-templateee\\src\\test\\resources\\write\\result.txt");

    String expected = """
        C:\\Users\\HOME\\IdeaProjects\\special-computing-workshop-templateee\\src\\test\\resources\\read\\3.txt
        C:\\Users\\HOME\\IdeaProjects\\special-computing-workshop-templateee\\src\\test\\resources\\read\\dir
        C:\\Users\\HOME\\IdeaProjects\\special-computing-workshop-templateee\\src\\test\\resources\\read\\dir\\2.txt
        C:\\Users\\HOME\\IdeaProjects\\special-computing-workshop-templateee\\src\\test\\resources\\read\\dir\\directory
        C:\\Users\\HOME\\IdeaProjects\\special-computing-workshop-templateee\\src\\test\\resources\\read\\dir\\directory\\1.txt
        C:\\Users\\HOME\\IdeaProjects\\special-computing-workshop-templateee\\src\\test\\resources\\read\\dir\\directory\\empty
        """;

    FilePrint.scanToFile(pathToScan, pathToSave);
    String actual = Files.readString(Path.of(pathToSave.toUri()));

    Assertions.assertEquals(expected, actual);
  }

  @Test
  void emptyTest() throws IOException {
    Path pathToScan = Path.of("C:\\Users\\HOME\\IdeaProjects\\special-computing-workshop-templateee\\src\\test\\resources\\read\\dir\\directory\\empty");
    Path pathToSave = Path.of("C:\\Users\\HOME\\IdeaProjects\\special-computing-workshop-templateee\\src\\test\\resources\\write\\result.txt");

    FilePrint.scanToFile(pathToScan, pathToSave);
    String actual = Files.readString(Path.of(pathToSave.toUri()));

    Assertions.assertEquals("", actual);
  }

  @Test
  void stringScanErrorTest() {
    String pathToScan = "C:\\Users\\HOME\\IdeaProjects\\special-computing-workshop-templateee\\src\\test\\resources\\read\\dir\\directory\\empty2";
    String pathToSave = "C:\\Users\\HOME\\IdeaProjects\\special-computing-workshop-templateee\\src\\test\\resources\\write\\result.txt";

    Exception e = Assertions.assertThrows(RuntimeException.class,
        () -> FilePrint.scanToFile(pathToScan, pathToSave));

    Assertions.assertEquals("The path is not right",
        e.getMessage());
  }

  @Test
  void stringSaveErrorTest() {
    String pathToScan = "C:\\Users\\HOME\\IdeaProjects\\special-computing-workshop-templateee\\src\\test\\resources\\read";
    String pathToSave = "C:\\Users\\HOME\\IdeaProjects\\special-computing-workshop-templateee\\src\\test\\resources\\write\\resul2222t.txt";

    Exception e = Assertions.assertThrows(RuntimeException.class,
        () -> FilePrint.scanToFile(pathToScan, pathToSave));

    Assertions.assertEquals("The path is not right",
        e.getMessage());
  }
}
