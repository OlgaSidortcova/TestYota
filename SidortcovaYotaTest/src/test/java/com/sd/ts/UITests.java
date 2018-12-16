package com.sd.ts;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UITests extends TestBase {

  @Test //(enabled = false)
  public void testIncreaseBalance() {
    double balance = 500.0;
    double balanceBefore =((int) Double.parseDouble (applicationManager.getBalance() )* 100) * 0.01;

    applicationManager.SetBalance(balance);
    applicationManager.IncreaseBalance();

    double balanceAfter =((int) Double.parseDouble (applicationManager.getBalance() )* 100) * 0.01;

    assertEquals(balanceBefore + balance, balanceAfter);
  }

  @Test// (enabled = false)
  public void testPurchase() {
    applicationManager.SliderIncrease();
    String cost = applicationManager.getCurrentCost();

    double balanceBefore =((int) Double.parseDouble (applicationManager.getBalance() )* 100) * 0.01;

    applicationManager.SetBalance(Double.parseDouble(cost));
    applicationManager.IncreaseBalance();
    applicationManager.Purchase();

    double balanceAfter =((int) Double.parseDouble (applicationManager.getBalance() )* 100) * 0.01;

    String speed = applicationManager.getSpeed();
    String currentSpeed = applicationManager.getCurrentSpeed();

    String currentCost = applicationManager.getCurrentCost();
    String costNoArrow = applicationManager.getCostNoArrow();

    assertEquals(balanceBefore, balanceAfter);
    assertEquals(speed, currentSpeed);
    assertEquals(currentCost, costNoArrow);

  }

  @Test
  public void testReset() {
    applicationManager.SliderIncrease();
    int balance = 1000;
    applicationManager.SetBalance(balance);
    applicationManager.IncreaseBalance();
    applicationManager.Purchase();
    applicationManager.Reset();

    double balanceAfter =((int) Double.parseDouble (applicationManager.getBalance() )* 100) * 0.01;

    String speed = applicationManager.getSpeed();
    String currentSpeed = applicationManager.getCurrentSpeed();

    String currentCost = applicationManager.getCurrentCost();
    String costNoArrow = applicationManager.getCostNoArrow();

    assertEquals(speed, currentSpeed);
    assertEquals(currentCost, costNoArrow);

    assertEquals(balanceAfter, 0.0);

  }


}
