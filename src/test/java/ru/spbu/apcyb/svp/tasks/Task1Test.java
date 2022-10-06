package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task1Test {
  
  @Test
  void readNumb1() {
    
    String str = "15";
    
    long exp = 15;
    long act = Task1.readNumb(str);
    
    assertEquals(exp, act);
  }
  @Test
  void readNumb2() {
  
    boolean thrown = false;
    String str = "-1";

    try {
      Task1.readNumb(str);
    } catch (Exception e) {
      thrown = true;
    }
  
    assertTrue(thrown);
  }
  
  @Test
  void readNumb3() {
    
    boolean thrown = false;
    String str = "a";
    
    try {
      Task1.readNumb(str);
    } catch (Exception e) {
      thrown = true;
    }
    
    assertTrue(thrown);
  }
  @Test
  void readVal1() {
    
    String str = "1 2 3 4 5";
    
    long[] exp = {1, 2, 3, 4, 5};
    long[] act = Task1.readVal(str);
    
    assertArrayEquals(exp, act);
  }
  
  @Test
  void readVal2() {
  
    boolean thrown = false;
    String str = "1 a 3";
  
    try {
      Task1.readVal(str);
    } catch (Exception e) {
      thrown = true;
    }
  
    assertTrue(thrown);
  }
  
  @Test
  void transformVal1() {
      
      long numb = 15;
      long[] val = {2, 3, 5, 10, 15, 2, 5, 20};
    
      long[] exp = {2, 3, 5, 10, 15};
      long[] act = Task1.transformVal(numb, val);
    
      assertArrayEquals(exp, act);
  }
  
  @Test
  void transformVal2() {
    boolean thrown = false;
    long numb = 15;
    long[] val = {-1, 2, 10};
    
    
    try {
      Task1.transformVal(numb, val);
    } catch (Exception e) {
      thrown = true;
    }
  
    assertTrue(thrown);
  }
  
  @Test
  void transformVal3() {
    boolean thrown = false;
    long numb = 15;
    long[] val = {16, 17, 20};
  
  
    try {
      Task1.transformVal(numb, val);
    } catch (Exception e) {
      thrown = true;
    }
  
    assertTrue(thrown);
  }
  
  @Test
  void cutMassive() {
    
    int n = 5;
    long[] mas = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    
    long[] exp = {1, 2, 3, 4, 5};
    long[] act = Task1.cutMassive(mas, n);
    
    assertArrayEquals(exp, act);
  }
  
  @Test
  void exchange() {
    long constNumb = 12;
    long numb = 12;
    long[] val = {2, 3, 4};
    long[] count = new long[val.length];
    int helper = 0;
    long n = 0;
    
    long exp = 7;
    long act = Task1.exchange(constNumb, numb, val, count, helper, n);
    
    assertEquals(exp, act);
  }
}