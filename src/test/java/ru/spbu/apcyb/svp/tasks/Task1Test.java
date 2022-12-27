package ru.spbu.apcyb.svp.tasks;


import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;


class Task1Test {

    @Test
    void correctInput() {
        System.setIn(new ByteArrayInputStream("3".getBytes()));
        int n = Task1.correctInput();
        System.setIn(System.in);
        assertEquals(3, n);
    }

    @Test
    void enterSum() {
        System.setIn(new ByteArrayInputStream("5".getBytes()));
        int n = Task1.enterSum();
        System.setIn(System.in);
        assertEquals(5, n);
    }

    @Test
    void enterSum1() {
        System.setIn(new ByteArrayInputStream("asadasd".getBytes()));
        System.setIn(System.in);
        IllegalArgumentException thrown =
                assertThrows(IllegalArgumentException.class, () -> Task1.enterSum());
        assertEquals("Ошибка ввода!", thrown.getMessage());
    }

    @Test
    void enterAmount1() {
        System.setIn(new ByteArrayInputStream("10".getBytes()));
        long actual = Task1.enterAmount();
        System.setIn(System.in);
        long expected = 10;
        assertEquals(expected, actual);
    }

    @Test
    void enterAmount2() {
        System.setIn(new ByteArrayInputStream(" ".getBytes()));
        assertThrows(IllegalArgumentException.class, Task1::enterSum);
        System.setIn(System.in);
    }

    @Test
    void inputOfCoins() {
        IllegalArgumentException thrown =
                assertThrows(IllegalArgumentException.class, () -> Task1.inputOfCoins(3,2));
        assertEquals("Ошибка ввода!", thrown.getMessage());
    }

    @Test
    void output() {
        int[] coins = {1};
        int[] use = {0, 2, 3};
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Task1.output(coins, use, 5));
    }

    @Test
    void positivityValidation1() {
        assertEquals(6, Task1.positivityValidation(6));
    }

    @Test
    void positivityValidation2() {
        assertDoesNotThrow(() -> Task1.positivityValidation(1));
    }

    @Test
    void checkingTooBigElements() {
        int[] coins = {1, 5, 6};
        IllegalArgumentException thrown =
                assertThrows(IllegalArgumentException.class, () -> Task1.checkingTooBigElements(coins[2],2));
        assertEquals("Ошибка ввода!", thrown.getMessage());
    }

    @Test
    void checkingTooBigElements1() {
        int[] coins = {4, 5, 6};
        int actual = Task1.checkingTooBigElements(coins[0], 5);
        assertEquals(4, actual);
    }

    @Test
    void searchOfOptions() {
        int[] coins = {1, 2};
        int[] use = new int[coins.length];
        int actual = Task1.searchForCombinations(5, 5, coins, use, 0, 0);
        assertEquals(3, actual);
    }

    @Test
    void searchForCombinations1() {
        int[] coins = {0};
        int[] use = new int[coins.length];
        assertThrows(ArithmeticException.class,
                () -> Task1.searchForCombinations(1, 1, coins, use, 0, 0));
    }


    @Test
    void searchForCombinations2() {
        int[] coins = {5};
        int[] use = new int[coins.length];
        long actual = Task1.searchForCombinations(5, 0, coins, use, 0, 0);
        assertEquals(1, actual);
    }

    @Test
    void searchForCombinations3() {
        int[] coins = {1};
        int[] use = new int[coins.length];
        long actual = Task1.searchForCombinations(-4, -4, coins, use, 0, 0);
        assertEquals(0, actual);
    }

    @Test
    void searchForCombinations4() {
        int[] coins = {1};
        int[] use = new int[coins.length];
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> Task1.searchForCombinations(5, 5, coins, use, 3, 0));
    }

    @Test
    void searchForCombinations5() {
        int[] coins = {1};
        int[] use = {0, 1, 2};
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> Task1.searchForCombinations(5, 5, coins, use, 0, 0));
    }

    @Test
    void searchForCombinations6() {
        int[] coins = {1, 2, 3};
        int[] use = {0};
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> Task1.searchForCombinations(5, 5, coins, use, 0, 0));
    }



}
