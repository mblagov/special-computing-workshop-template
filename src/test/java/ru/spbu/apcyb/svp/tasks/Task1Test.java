package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class Task1Test {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outContent));
  }

  @Test
  public void testNegativeChangeException() {
    String input = "-5\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    assertThrows(ArithmeticException.class, () -> Task1.main(null));
  }

  @Test
  public void testNegativeNumDenominatorsException() {
    String input = "5\n-3\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    assertThrows(ArithmeticException.class, () -> Task1.main(null));
  }

  @Test
  public void testNegativeDenominatorException() {
    String input = "5\n2\n3\n-1\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    assertThrows(ArithmeticException.class, () -> Task1.main(null));
  }

  @Test
  public void testAllOK() {
    String input = "5\n3\n1\n2\n5";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    Task1.main(null);
    assertTrue(outContent.toString().contains("5"));
  }
}
