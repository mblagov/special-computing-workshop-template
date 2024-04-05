package ru.spbu.apcyb.svp.tasks.task2;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class linkedlistTest {

    linkedlist list = new linkedlist();

    @Test
    void isEmptyTest() {
        assertTrue(list.isEmpty());
    }

    @Test
    void isNotEmptyTest() {
        list.add(11);
        assertFalse(list.isEmpty());
    }

    @Test
    void Contains_elementTest() {
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);
        assertTrue(list.contains(11));
    }

    @Test
    void SizeOfListTest(){
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);
        assertEquals(4, list.size());
    }

    @Test
    void NotContains_elementTest() {
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);
        assertFalse(list.contains(15));
    }

    @Test
    void Removing_elementTest() {
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);
        assertTrue(list.contains(33));
        list.remove(2);
        assertFalse(list.contains(33));
    }

    @Test
    void Getting_elementTest() {
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);
        assertEquals(11, list.get(0));
    }

    @Test
    void AddingByIndexTest(){
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);
        list.add(3, 55);
        assertEquals(55, list.get(3));
    }

    @Test
    void Removing_elementIndexOfBoundsException(){
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(6));
    }

    @Test
    void Getting_elementIndexOfBoundsException(){
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(6));
    }

    @Test
    void AddingByIndexIndexOfBoundsException(){
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(6, 77));
    }

    @Test
    void UnsupportedOperationExceptionTest(){
        assertThrows(UnsupportedOperationException.class, () -> list.clear());
    }
}