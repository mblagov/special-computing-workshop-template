package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Task1Tests {
    @Test
    public void noInput() {
        Task1 task1 = new Task1();
        ByteArrayInputStream in = new ByteArrayInputStream("\n".getBytes());
        System.setIn(in);
        IOException e = Assertions.assertThrows(
                IOException.class,
                () -> task1.getInput(),
                "No input exception must be thrown");
        Assertions.assertEquals("At least 2 numbers are required: result sum and bill value", e.getMessage());
    }

    @Test
    void noBills() {
        Task1 task1 = new Task1();
        ByteArrayInputStream in = new ByteArrayInputStream("10".getBytes());
        System.setIn(in);
        IOException e = Assertions.assertThrows(
                IOException.class,
                () -> task1.getInput(),
                "No bills exception must be thrown");
        Assertions.assertEquals("At least 1 bill is required", e.getMessage());
    }

    @Test
    void badInputBills() {
        Task1 task1 = new Task1();
        ByteArrayInputStream in = new ByteArrayInputStream("1354145 4 5 fa".getBytes());
        System.setIn(in);
        NumberFormatException e = Assertions.assertThrows(
                NumberFormatException.class,
                () -> task1.getInput(),
                "Only positive bills exception must be thrown");
    }

    @Test
    void badInputSum() {
        Task1 task1 = new Task1();
        ByteArrayInputStream in = new ByteArrayInputStream("de12 4 5 fa".getBytes());
        System.setIn(in);
        NumberFormatException e = Assertions.assertThrows(
                NumberFormatException.class,
                () -> task1.getInput(),
                "Only positive sum exception must be thrown");
    }

    @Test
    void negativeSum() {
        Task1 task1 = new Task1();
        ByteArrayInputStream in = new ByteArrayInputStream("-5 1 1".getBytes());
        System.setIn(in);
        IOException e = Assertions.assertThrows(
                IOException.class,
                () -> task1.getInput(),
                "Only positive sum exception must be thrown");
        Assertions.assertEquals("Result sum must be positive", e.getMessage());
    }

    @Test
    void negativeBill() {
        Task1 task1 = new Task1();
        ByteArrayInputStream in = new ByteArrayInputStream("5 1 -1 55".getBytes());
        System.setIn(in);
        IOException e = Assertions.assertThrows(
                IOException.class,
                () -> task1.getInput(),
                "Only positive bill exception must be thrown");
        Assertions.assertEquals("Bill value must be positive", e.getMessage());
    }

    @Test
    void zeroBill() {
        Task1 task1 = new Task1();
        ByteArrayInputStream in = new ByteArrayInputStream("5 1 0 55".getBytes());
        System.setIn(in);
        IOException e = Assertions.assertThrows(
                IOException.class,
                () -> task1.getInput(),
                "Only positive bill exception must be thrown");
        Assertions.assertEquals("Bill value must be positive", e.getMessage());
    }


    @Test
    void sameBills() throws Exception {
        System.out.println("Same bills test (5 1 1)");
        Task1 task1 = new Task1();
        ByteArrayInputStream in = new ByteArrayInputStream("5 1 1".getBytes());
        System.setIn(in);
        task1.getInput();
        long k = task1.findPossibleChanges();
        System.out.println(k);
        Assertions.assertEquals(1, k);
        System.out.println();
    }

    @Test
    void sumLessThanBills() throws Exception {
        System.out.println("Sum < bills test (5 10 6)");
        Task1 task1 = new Task1();
        ByteArrayInputStream in = new ByteArrayInputStream("5 10 6".getBytes());
        System.setIn(in);
        task1.getInput();
        long k = task1.findPossibleChanges();
        Assertions.assertEquals(0, k);
        System.out.println();
    }

    @Test
    void checkSmall1() throws Exception {
        System.out.println("Small test (5 2 3)");
        Task1 task1 = new Task1();
        ByteArrayInputStream in = new ByteArrayInputStream("5 2 3".getBytes());
        System.setIn(in);
        task1.getInput();
        long k = task1.findPossibleChanges();
        Assertions.assertEquals(1, k);
        System.out.println();
    }

    @Test
    void checkSmall2() throws Exception {
        System.out.println("Small test (4 2 1)");
        Task1 task1 = new Task1();
        ByteArrayInputStream in = new ByteArrayInputStream("4 2 1".getBytes());
        System.setIn(in);
        task1.getInput();
        long k = task1.findPossibleChanges();
        Assertions.assertEquals(3, k);
        System.out.println();
    }

    @Test
    void checkSmall22() throws Exception {
        System.out.println("Small test (4 1 2)");
        Task1 task1 = new Task1();
        ByteArrayInputStream in = new ByteArrayInputStream("4 1 2".getBytes());
        System.setIn(in);
        task1.getInput();
        long k = task1.findPossibleChanges();
        Assertions.assertEquals(3, k);
        System.out.println();
    }

    @Test
    void checkBig2() throws Exception {
        System.out.println("Big test (1000 1)");
        Task1 task1 = new Task1();
        ByteArrayInputStream in = new ByteArrayInputStream("1000 1".getBytes());
        System.setIn(in);
        task1.getInput();
        long k = task1.findPossibleChanges();
        Assertions.assertEquals(1, k);
        System.out.println();
    }

    @Test
    void checkBig3() throws Exception {
        System.out.println("Big test (1000 500 1)");
        Task1 task1 = new Task1();
        ByteArrayInputStream in = new ByteArrayInputStream("1000 500 1".getBytes());
        System.setIn(in);
        task1.getInput();
        long k = task1.findPossibleChanges();
        Assertions.assertEquals(3, k);
        System.out.println();
    }

    @Test
    void billEqualsSum() throws Exception {
        System.out.println("Sum==Bill test (3000000000 3000000000)");
        Task1 task1 = new Task1();
        ByteArrayInputStream in = new ByteArrayInputStream("3000000000 3000000000".getBytes());
        System.setIn(in);
        task1.getInput();
        long k = task1.findPossibleChanges();
        Assertions.assertEquals(1, k);
        System.out.println();
    }
}
