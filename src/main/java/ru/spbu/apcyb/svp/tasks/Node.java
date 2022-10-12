package ru.spbu.apcyb.svp.tasks;

/**
 * Узел.
 */

public class Node {
  protected Object data;
  protected Node next;
  protected Node prev;

  public Node(Object data) {
    this.data = data;
  }

}
