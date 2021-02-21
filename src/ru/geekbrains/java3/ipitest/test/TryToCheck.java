package ru.geekbrains.java3.ipitest.test;

import ru.geekbrains.java3.ipitest.test.annotations.AfterSuite;
import ru.geekbrains.java3.ipitest.test.annotations.BeforeSuite;
import ru.geekbrains.java3.ipitest.test.annotations.MyTest;
import ru.geekbrains.java3.ipitest.test.annotations.Test;

@MyTest
public class TryToCheck {

  @BeforeSuite
  public void set() {
    System.out.println("@BeforeSuite");
  }

  @Test(prior = 1)
  public void testIntEquals() {
    System.out.println("Тест 1");
  }

  @Test(prior = 0)
  public int testIntEquals1() {
    System.out.println("Тест 2");
    return 1;
  }

  @Test(prior = 0)
  public void testIntEquals3() {
    System.out.println("Тест 0");

  }


  @AfterSuite
  public void close() {
    System.out.println("@AfterSuite");
  }
}
