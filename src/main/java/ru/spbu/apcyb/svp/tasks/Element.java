package ru.spbu.apcyb.svp.tasks;

/**
 * Задание 2.
 */

public class Element {
  
  protected Object obj;
  
  public Element(Object obj) {
    this.obj = obj;
  }
  
  protected Element next;
  protected Element prev;
  
}

