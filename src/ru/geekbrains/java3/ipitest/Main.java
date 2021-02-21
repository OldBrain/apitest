package ru.geekbrains.java3.ipitest;


import ru.geekbrains.java3.ipitest.test.Start;
import ru.geekbrains.java3.ipitest.test.TryToCheck;

import java.lang.reflect.InvocationTargetException;

public class Main {


  public static void main(String[] args)  {

    try {
      Start.StartTest(TryToCheck.class);
    } catch (
                NoSuchMethodException |
               IllegalAccessException |
            InvocationTargetException |
               InstantiationException e) {
      e.printStackTrace();
    }  
  }
}
