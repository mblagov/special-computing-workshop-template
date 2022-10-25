package ru.spbu.apcyb.svp.tasks;



/**
 * Задание 2.
 */
public class ArrayListStack extends java.util.Stack<Object> {

  private final transient ArrayList stack;

  public ArrayListStack() {
    stack = new ArrayList();
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


