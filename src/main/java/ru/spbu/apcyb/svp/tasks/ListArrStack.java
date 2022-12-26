package ru.spbu.apcyb.svp.tasks;

public class ListArrStack extends java.util.Stack<Object>{
  private final transient ListArr stack;

  public ListArrStack() {
    stack = new ListArr();
  }

  @Override
  public Object push(Object data) {
    stack.stackPush(data);
    return data;
  }

  @Override
  public synchronized Object pop() {
    return stack.stackPop();
  }

  @Override
  public synchronized Object peek() {
    return stack.stackPeek();
  }

  @Override
  public synchronized boolean isEmpty() {
    return stack.isEmpty();
  }
}