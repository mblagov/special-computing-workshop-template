package ru.spbu.apcyb.svp.tasks;



/**
 * Задание 2.
 */
public class LinkedListArrStack extends java.util.Stack<Object> {

  private final transient LinkedListArr stack;

  public LinkedListArrStack() {
    stack = new LinkedListArr();
  }

  @Override
    public Object push(Object data) {
    stack.addFirst(data);
    return data;
  }

  @Override
    public synchronized Object pop() {
    return stack.removeFirst();
  }

  @Override
    public synchronized Object peek() {
    return stack.getFirst();
  }

  @Override
    public synchronized boolean isEmpty() {
    return stack.isEmpty();
  }

}


