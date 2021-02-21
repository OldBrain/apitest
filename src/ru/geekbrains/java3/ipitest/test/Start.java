package ru.geekbrains.java3.ipitest.test;

import ru.geekbrains.java3.ipitest.test.annotations.AfterSuite;
import ru.geekbrains.java3.ipitest.test.annotations.BeforeSuite;
import ru.geekbrains.java3.ipitest.test.annotations.MyTest;
import ru.geekbrains.java3.ipitest.test.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Start {
  private static Object testClass;
  private static List<Integer> prior = new ArrayList<>();

  public static void StartTest(Class cl) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

    testClass = cl.getConstructor().newInstance();

    if (!cl.isAnnotationPresent(MyTest.class)) {
      throw new RuntimeException(cl.getName() + " не является классом теста.");
    }

    Method[] met = cl.getMethods();
    int bfs = 0;
    int afs = 0;

    for (Method m : met) {
      if (m.getAnnotation(AfterSuite.class) != null) {
        if (afs == 0) {
          afs++;
        } else {
          throw new RuntimeException(cl.getName() + "повторная аннотация AfterSuite");
        }
      }
      if (m.getAnnotation(BeforeSuite.class) != null) {
        if (bfs == 0) {
          bfs++;
        } else {
          throw new RuntimeException(cl.getName() + "повторная аннотация BeforeSuite");
        }
      }

      if (m.getAnnotation(Test.class) != null) {
        prior.add(m.getAnnotation(Test.class).prior());
      }
    }

    Collections.sort(prior);
    Collections.reverse(prior);
    Method[] bfrS = new Method[1];

    for (Method m : met) {

      if (m.getAnnotation(BeforeSuite.class) != null) {
        m.invoke(testClass);
      }

      if (m.getAnnotation(BeforeSuite.class) != null) {
        bfrS[0] = m;
      }
      if (m.getAnnotation(Test.class) != null) {
        for (int i = 0; i < prior.size(); i++) {
          if (prior.get(i).equals(m.getAnnotation(Test.class).prior())) {
            m.invoke(testClass);
            prior.remove(prior.get(i));
          }
        }
      }
    }

    bfrS[0].invoke(testClass);
  }
}





