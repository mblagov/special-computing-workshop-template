package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExampleTestClass {
    @Test
    public void testSum() {
        ExampleMainClass sut = new ExampleMainClass();

        assertEquals(4, sut.sum(2, 2));
    }

    @Test
    public void testProduct() {
        ExampleMainClass sut = new ExampleMainClass();

        assertEquals(4, sut.product(2, 2));
    }
}
