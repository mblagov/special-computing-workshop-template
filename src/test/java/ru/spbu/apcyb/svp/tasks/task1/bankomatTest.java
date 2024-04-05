package ru.spbu.apcyb.svp.tasks.task1;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ru.spbu.apcyb.svp.tasks.task1.bankomat.inputLongList;

class bankomatTest {

    @Test
    void AmountIsNegative() {
        assertThrows(NumberFormatException.class, () -> {bankomat.coinChange("-5", "2 1");}
        );
    }

    @Test
    void OneBanknoteIsNegative() {
        assertThrows(NumberFormatException.class, () -> {bankomat.coinChange("5", "-2 1");}
        );
    }

    @Test
    void BothBanknotesAreNegative() {
        assertThrows(NumberFormatException.class, () -> {bankomat.coinChange("5", "-2 -1");}
        );
    }

    @Test
    void SumIsZero() {
        assertThrows(NumberFormatException.class, () -> {bankomat.coinChange("0", "-2 1");}
        );
    }

    @Test
    void BanknoteIsZero() {
        assertThrows(NumberFormatException.class, () -> {bankomat.coinChange("5", "0");}
        );
    }

    @Test
    void SumWasNotWritten() {
        assertThrows(NumberFormatException.class, () -> {bankomat.coinChange("", "-2 1");}
        );
    }

    @Test
    void BanknotesWereNotWritten() {
        assertThrows(NumberFormatException.class, () -> {bankomat.coinChange("5", "");}
        );
    }

    @Test
    void SumIsNotANumber() {
        assertThrows(NumberFormatException.class, () -> {bankomat.coinChange("abc", "1 5");}
        );
    }

    @Test
    void BanknotesAreNotNumbers() {
        assertThrows(NumberFormatException.class, () -> {bankomat.coinChange("10", "a b c");}
        );
    }

    @Test
    void Sums() {
        assertThrows(NumberFormatException.class, () -> {bankomat.coinChange("3+2", "1+1 2+1");}
        );
    }

    @Test
    void Sum5Banknotes3_2() {
        List<List<Long>> res = bankomat.coinChange("5", "3 2");
        List<List<Long>> ans = new ArrayList<>();
        ans.add(Arrays.stream(bankomat.inputLongList("3 2")).boxed().collect(Collectors.toList()));
        assertIterableEquals(res, ans);
    }

    @Test
    void OrderIndependence() {
        List<List<Long>> res1 = bankomat.coinChange("5", "3 2");
        List<List<Long>> res2 = bankomat.coinChange("5", "2 3");
        assertIterableEquals(res1, res2);
    }

    @Test
    void Thousand() {
        List<List<Long>> res =  bankomat.coinChange("1000", "1");
        assertFalse(res.size() == 0);
    }

    @Test
    void ThousandAndBanknotes() {
        List<List<Long>> res =  bankomat.coinChange("1000", "500 1");
        assertFalse(res.size() == 0);
    }

    @Test
    void ThreeBillion() {
        List<List<Long>> res = bankomat.coinChange("3000000000", "3000000000");
        List<List<Long>> ans = new ArrayList<>();
        ans.add(Arrays.stream(inputLongList("3000000000")).boxed().collect(Collectors.toList()));
        assertIterableEquals(res, ans);
    }

    @Test
    void OneOfBanknotesIsBiggerThanAmount() {
        List<List<Long>> res = bankomat.coinChange("5", "10 6");
        assertTrue(res.size() == 0);
    }

}