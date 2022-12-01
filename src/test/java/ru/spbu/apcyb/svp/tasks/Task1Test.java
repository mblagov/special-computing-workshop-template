package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.spbu.apcyb.svp.tasks.Task1.inputLongList;


/**
 * Тесты для задания 1.
 */
public class Task1Test {

    @Test
    void isBanknotes() {
        List<List<Long>> result = Task1.exchangeOfBanknotes("5",  "3 2");
        List<List<Long>> answer = new ArrayList<>();
        answer.add(Arrays.stream(inputLongList("3 2")).boxed().collect(Collectors.toList()));
        Assertions.assertIterableEquals(result, answer);
    }

    @Test
    void isIndependentOfTheOrder(){
        List<List<Long>> result1 = Task1.exchangeOfBanknotes("4",  "2 1");
        List<List<Long>> result2 = Task1.exchangeOfBanknotes("4",  "1 2");
        Assertions.assertIterableEquals(result1, result2);
    }

    @Test
    void isThousandBanknotes(){
        List<List<Long>> result = Task1.exchangeOfBanknotes("1000",  "1");
        Assertions.assertFalse(result.size() == 0);
    }

    @Test
    void isBillionAmount(){
        List<List<Long>> result = Task1.exchangeOfBanknotes("10000",  "2 1");
        Assertions.assertFalse(result.size() == 0);
    }

    @Test
    void isThousandAmount(){
        List<List<Long>> result = Task1.exchangeOfBanknotes("1000",  "500 1");
        Assertions.assertFalse(result.size() == 0);
    }

    @Test
    void isTooHighBills(){
        List<List<Long>> result = Task1.exchangeOfBanknotes("5",  "10 6");
        Assertions.assertTrue(result.size() == 0);
    }

    @Test
    void isBigBanknotes() {
        List<List<Long>> result = Task1.exchangeOfBanknotes("3000000000",  "3000000000");
        List<List<Long>> answer = new ArrayList<>();
        answer.add(Arrays.stream(inputLongList("3000000000")).boxed().collect(Collectors.toList()));
        Assertions.assertIterableEquals(result, answer);
    }

    @Test
    void isAmountIsEmpty() {
        Assertions.assertThrows(NumberFormatException.class, () -> {Task1.exchangeOfBanknotes("",  "2 1");});
    }

    @Test
    void isAmountIsZero() {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Task1.exchangeOfBanknotes("", "2 1");
        });
    }

    @Test
    void isBanknotesIsZero() {
        Assertions.assertThrows(NumberFormatException.class, () -> {Task1.exchangeOfBanknotes("5",  "0");});

    }


    @Test
    void isRepeatedBanknotes() {
        List<List<Long>> result = Task1.exchangeOfBanknotes("20",  "10 10");
        List<List<Long>> answer = new ArrayList<>();
        List<Long> answerInt = new ArrayList<>();
        answerInt.add(Long.valueOf(10));
        answerInt.add(Long.valueOf(10));
        answer.add(answerInt);
        Assertions.assertIterableEquals(result, answer);
    }

    @Test
    void isAmountIsNegative() {
        Assertions.assertThrows(NumberFormatException.class, () -> {Task1.exchangeOfBanknotes("-5",  "2 1");});

    }

    @Test
    void isBanknoteIsNegative() {
        Assertions.assertThrows(NumberFormatException.class, () -> {Task1.exchangeOfBanknotes("5",  "-1 1");});

    }

    @Test
    void isIncorrectInputOfAmount() {
        Assertions.assertThrows(NumberFormatException.class, () -> {Task1.exchangeOfBanknotes("abc",  "1 2");});

    }

    @Test
    void isIncorrectInputOfBanknotes() {
        Assertions.assertThrows(NumberFormatException.class, () -> {Task1.exchangeOfBanknotes("5",  "a b c");});

    }

    @Test
    void isSum() {
        Assertions.assertThrows(NumberFormatException.class, () -> {Task1.exchangeOfBanknotes("3+2",  "1+1 2+1");});

    }

}
