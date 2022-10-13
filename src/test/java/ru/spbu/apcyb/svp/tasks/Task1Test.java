package ru.spbu.apcyb.svp.tasks;

import org.eclipse.sisu.containers.InjectedTestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import ru.spbu.apcyb.svp.tasks.Task1;


/**
 * Тесты для задания 1.
 */
public class Task1Test {
    @Test
        //ярлычок для идеи)
    void isEmpty() {
        List<List<Integer>> result = Task1.exchangeOfBanknotes(90,  new int[]{20});
        List<List<Integer>> answer = new ArrayList<>();
        Assertions.assertIterableEquals(result, answer);
    }

    @Test
    void isRepeatedBanknotes() {
        List<List<Integer>> result = Task1.exchangeOfBanknotes(20,  new int[]{10, 10});
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> answerInt = new ArrayList<>();
        answerInt.add(10);
        answerInt.add(10);
        answer.add(answerInt);
        Assertions.assertIterableEquals(result, answer);
    }

    @Test
    void isBanknotes() {
        List<List<Integer>> result = Task1.exchangeOfBanknotes(20,  new int[]{10});
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> answerInt = new ArrayList<>();
        answerInt.add(10);
        answerInt.add(10);
        answer.add(answerInt);
        Assertions.assertIterableEquals(result, answer);
    }

    @Test
    void isBigBanknotes() {
        List<List<Integer>> result = Task1.exchangeOfBanknotes(20,  new int[]{30});
        List<List<Integer>> answer = new ArrayList<>();
        Assertions.assertIterableEquals(result, answer);
    }
}
