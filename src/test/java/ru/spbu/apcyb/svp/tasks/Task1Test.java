package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.*;

class Task1Test {
  
  @Test
  void readNumb1() {
    
    System.setIn(new ByteArrayInputStream("15".getBytes()));
    
    assertEquals(15, Task1.readNumb());
    System.setIn(System.in);
  }
  @Test
  void readNumb2() {
    
    System.setIn(new ByteArrayInputStream("-1".getBytes()));
    
    assertThrows(RuntimeException.class, Task1::readNumb);
    System.setIn(System.in);
  }
  
  @Test
  void readNumb3() {
  
    System.setIn(new ByteArrayInputStream("a".getBytes()));
    
    assertThrows(RuntimeException.class, Task1::readNumb);
    System.setIn(System.in);
  }
  
  @Test
  void readVal1() {
    
    System.setIn(new ByteArrayInputStream("1 2 3".getBytes()));
    long[] expected = {1, 2, 3};
    
    long[] actual = Task1.readVal();
    System.setIn(System.in);

    assertArrayEquals(expected, actual);
  }
  
  @Test
  void readVal2() {
  
    System.setIn(new ByteArrayInputStream("1 a 3".getBytes()));
    boolean thrown = false;
    
    try {
      Task1.readVal();
      System.setIn(System.in);
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
  void transformVal4() {
    
    long numb = 15;
    long[] val = {2, 3, 5};
    
    long[] exp = {2, 3, 5};
    long[] act = Task1.transformVal(numb, val);
    
    assertArrayEquals(exp, act);
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