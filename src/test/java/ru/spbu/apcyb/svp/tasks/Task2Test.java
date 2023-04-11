package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import ru.spbu.apcyb.svp.tasks.task2.DoublyLinkedList;
import ru.spbu.apcyb.svp.tasks.task2.ListNode;
import ru.spbu.apcyb.svp.tasks.task2.MyQueue;

/**
 * Тесты для задания 2.
 */
@TestInstance(Lifecycle.PER_CLASS)
class Task2Test {
  private ListNode<Integer> previousNode;
  private ListNode<Integer> nextNode;

  DoublyLinkedList listInit() {
    var list = new DoublyLinkedList<>();
    list.add(5);
    list.add(0, 3);
    return list;
  }

  ListNode nodesInit() {
    previousNode = new ListNode<>(1);
    var currentNode = new ListNode<>(2);
    nextNode = new ListNode<>(3);
    currentNode.setNext(nextNode);
    currentNode.setPrevious(previousNode);
    return currentNode;
  }

  MyQueue queueInit() {
    var queue = new MyQueue<>(new DoublyLinkedList<>());
    return queue;
  }

  @Test
  void task2Test1() {
    var currentNode = nodesInit();
    Assertions.assertEquals(2, currentNode.getData());
    currentNode.setData(4);
    Assertions.assertEquals(4, currentNode.getData());
    Assertions.assertEquals(nextNode, currentNode.getNext());
    Assertions.assertEquals(previousNode, currentNode.getPrevious());
  }

  @Test
  void task2Test2() {
    var list = listInit();
    Assertions.assertEquals(3, list.getHead().getData());
    Assertions.assertEquals(5, list.getTail().getData());
  }

  @Test
  void task2Test3() {
    var list = listInit();
    list.remove(1);
    Assertions.assertEquals(3, list.getTail().getData());
  }

  @Test
  void task2Test4() {
    var list = listInit();
    Assertions.assertEquals(true, list.contains(3));
  }

  @Test
  void task2Test5() {
    var list = listInit();
    Assertions.assertEquals(3, list.get(0));
  }

  @Test
  void task2Test6() {
    var list = listInit();
    Assertions.assertEquals(false, list.isEmpty());
    list.remove(0);
    list.remove(0);
    Assertions.assertEquals(true, list.isEmpty());
  }

  @Test
  void task2Test7() {
    var queue = queueInit();
    Assertions.assertEquals(true, queue.isEmpty());
  }

  @Test
  void task2Test8() {
    var queue = queueInit();
    queue.add(2);
    queue.add(3);
    Assertions.assertEquals(2, queue.peek());
  }

  @Test
  void task2Test9() {
    var list = new DoublyLinkedList<Integer>();
    Assertions.assertNull(list.getTail());
    Assertions.assertNull(list.getHead());
  }
}
