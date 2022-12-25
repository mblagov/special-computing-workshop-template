package ru.spbu.apcyb.svp.tasks;

public class ListArrStack extends java.util.Stack<Object>{
  private final transient ListArr stack;

  public ListArrStack() {
    stack = new ListArr();
  }

  @Override
  public Object push(Object data) {
    stack.Push(data);
    return data;
  }

  @Override
  public synchronized Object pop() {
    return stack.Pop();
  }

  @Override
  public synchronized Object peek() {
    return stack.Peek();
  }

  @Override
  public synchronized boolean isEmpty() {
    return stack.isEmpty();
  }
}