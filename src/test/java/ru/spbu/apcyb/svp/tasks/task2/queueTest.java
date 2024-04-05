package ru.spbu.apcyb.svp.tasks.task2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class queueTest {

    queue q = new queue();

    @Test
    void isEmptyTest() {
        assertTrue(q.isEmpty());
    }

    @Test
    void isNotEmptyTest() {
        q.add(11);
        assertFalse(q.isEmpty());
    }

    @Test
    void SizeOfQueueTest(){
        q.add(11);
        q.add(22);
        q.add(33);
        q.add(44);
        assertEquals(4, q.size());
    }

    @Test
    void FirstFromQueueTest(){
        q.add(11);
        q.add(22);
        q.add(33);
        q.add(44);
        assertEquals(11, q.peek());
    }

    @Test
    void UnsupportedOperationExceptionTest(){
        assertThrows(UnsupportedOperationException.class, () -> q.clear());
    }

}