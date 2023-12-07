package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class AtmTest {
    @Test
    public void test1() {
        Atm changeMachine = new Atm();
        changeMachine.denominations = Arrays.asList(2L, 3L);
        changeMachine.sum = 5;
        int expected = 1;
        int result = changeMachine.getNumberOfCombinations(changeMachine.findCombinations(changeMachine.sum, 0));
        assertEquals(expected, result);
    }

    @Test
    public void test2() {
        Atm changeMachine = new Atm();
        changeMachine.denominations = Arrays.asList(2L, 1L);
        changeMachine.sum = 4;
        int expected = 3;
        int result = changeMachine.getNumberOfCombinations(changeMachine.findCombinations(changeMachine.sum, 0));
        assertEquals(expected, result);
    }

    @Test
    public void test3() {
        Atm changeMachine = new Atm();
        changeMachine.denominations = Arrays.asList(1L, 2L);
        changeMachine.sum = 4;
        int expected = 3;
        int result = changeMachine.getNumberOfCombinations(changeMachine.findCombinations(changeMachine.sum, 0));
        assertEquals(expected, result);
    }

    @Test
    public void test4() {
        Atm changeMachine = new Atm();
        changeMachine.denominations = List.of(1L);
        changeMachine.sum = 1000;
        int expected = 1;
        int result = changeMachine.getNumberOfCombinations(changeMachine.findCombinations(changeMachine.sum, 0));
        assertEquals(expected, result);
    }

    @Test
    public void test5() {
        Atm changeMachine = new Atm();
        changeMachine.denominations = Arrays.asList(1L, 500L);
        changeMachine.sum = 1000;
        int expected = 3;
        int result = changeMachine.getNumberOfCombinations(changeMachine.findCombinations(changeMachine.sum, 0));
        assertEquals(expected, result);
    }

    @Test
    public void test6() {
        Atm changeMachine = new Atm();
        changeMachine.denominations = Arrays.asList(1L, 500L);
        changeMachine.sum = 1000;
        int expected = 3;
        int result = changeMachine.getNumberOfCombinations(changeMachine.findCombinations(changeMachine.sum, 0));
        assertEquals(expected, result);
    }

    @Test
    public void test7() {
        Atm changeMachine = new Atm();
        changeMachine.denominations = List.of(10000L);
        changeMachine.sum = 10;
        int expected = 0;
        int result = changeMachine.getNumberOfCombinations(changeMachine.findCombinations(changeMachine.sum, 0));
        assertEquals(expected, result);
    }

    @Test
    public void test8() {
        Atm changeMachine = new Atm();
        changeMachine.denominations = Arrays.asList(1L, 2L);
        changeMachine.sum = 0;
        assertThrows(IllegalArgumentException.class, () -> changeMachine.findCombinations(changeMachine.sum, 0));
    }

    @Test
    public void test9() {
        Atm changeMachine = new Atm();
        changeMachine.denominations = Arrays.asList(1L, 0L);
        changeMachine.sum = 10;
        assertThrows(IllegalArgumentException.class, () -> changeMachine.findCombinations(changeMachine.sum, 0));
    }

    @Test
    public void test10() {
        Atm changeMachine = new Atm();
        changeMachine.denominations = Arrays.asList(-1L, 3L);
        changeMachine.sum = 10;
        assertThrows(IllegalArgumentException.class, () -> changeMachine.findCombinations(changeMachine.sum, 0));
    }

    @Test
    public void test11() {
        Atm changeMachine = new Atm();
        changeMachine.denominations = null;
        changeMachine.sum = 10;
        assertThrows(IllegalArgumentException.class, () -> changeMachine.findCombinations(changeMachine.sum, 0));
    }







}
