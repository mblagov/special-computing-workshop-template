package ru.spbu.apcyb.svp.tasks.task1;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestAtm {

  @Test
  public void testParseCoins() {
    Atm sut = new Atm();

    Long[] coins = {Long.valueOf(2), Long.valueOf(3)};
    assertEquals(new ImmutablePair<>(Long.valueOf(10), Arrays.asList(coins)), sut.parseCoins(""));
  }
}
