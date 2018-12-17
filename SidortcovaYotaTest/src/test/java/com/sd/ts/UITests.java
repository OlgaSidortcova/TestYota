package com.sd.ts;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class UITests extends TestBase {

/*Тесты для регрессии должны проверять, что то, что работало до изменений кода продолжает работать.

Найдены баги и писать тесты, чтобы они падали до исправления кода, на мой взгляд, неразумно.
В данном случае, пусть тесты проверяют то, что работает.

Я бы проверяла основные возможности приложения.

1. Успешное пополнение баланса. testIncreaseBalance
2. Успешное подключение тарифа. testPurchase
3. Работоспособность кнопки "Сброс". testReset
4. Начальное состояние программы при первом запуске. testAFirstStart
5. Возможность подключить тариф, если баланс выше стоимости. Невозможность, если ниже. testCanPurchase
6. При нажатии на "+" стоимость и скорость тарифа повышаются. testIncreaseSlider
7. При нажатии на "-" стоимость и скорость тарифа понижаются. testDecreaseSlider

 */
  @Test
  public void testIncreaseBalance() {
    double balance = 500.0;
    double balanceBefore = ((int) Double.parseDouble(app.getBalance()) * 100) * 0.01;

    app.SetBalance(balance);
    app.IncreaseBalance();
    double balanceAfter = ((int) Double.parseDouble(app.getBalance()) * 100) * 0.01;

    assertEquals(balanceBefore + balance, balanceAfter);
  }

  @Test
  public void testPurchase() {
    app.SliderIncrease();
    String cost = app.getCurrentCost();

    double balanceBefore = ((int) Double.parseDouble(app.getBalance()) * 100) * 0.01;

    app.SetBalance(Double.parseDouble(cost));
    app.IncreaseBalance();
    app.Purchase();

    double balanceAfter = ((int) Double.parseDouble(app.getBalance()) * 100) * 0.01;
    assertEquals(balanceBefore, balanceAfter);

    String speed = app.getSpeed();
    String currentSpeed = app.getCurrentSpeed();
    assertEquals(speed, currentSpeed);

    String currentCost = app.getCurrentCost();
    String costNoArrow = app.getCostNoArrow();
    assertEquals(currentCost, costNoArrow);

    boolean purchaseEnabled = app.isEnabledPurchase();
    assertFalse(purchaseEnabled);

  }

  @Test
  public void testReset() {

    int balance = 1000;
    String baseSpeed = "64";
    String baseCost = "0";
    app.SliderIncrease();

    app.SetBalance(balance);
    app.IncreaseBalance();
    app.Purchase();
    app.Reset();

    double balanceAfter = ((int) Double.parseDouble(app.getBalance()) * 100) * 0.01;
    assertEquals(balanceAfter, 0.0);

    String speed = app.getSpeed();
    String currentSpeed = app.getCurrentSpeed();
    assertEquals(speed, currentSpeed);
    assertEquals(speed, baseSpeed);

    String currentCost = app.getCurrentCost();
    String costNoArrow = app.getCostNoArrow();
    assertEquals(currentCost, costNoArrow);
    assertEquals(currentCost, baseCost);

    boolean purchaseEnabled = app.isEnabledPurchase();
    assertFalse(purchaseEnabled);
  }

  @Test
  public void testAFirstStart() {

    String baseSpeed = "64";
    String baseCost = "0";

    boolean purchaseEnabled = app.isEnabledPurchase();

    String speed = app.getSpeed();
    String currentSpeed = app.getCurrentSpeed();
    assertEquals(speed, currentSpeed);
    assertEquals(speed, baseSpeed);

    String currentCost = app.getCurrentCost();
    String costNoArrow = app.getCostNoArrow();
    assertEquals(currentCost, costNoArrow);
    assertEquals(currentCost, baseCost);

    assertFalse(purchaseEnabled);
  }

  @Test
  public void testCanPurchase() {
    app.Reset();
    int balance = 500;
    app.SetBalance(balance);
    app.IncreaseBalance();
    double balanceBefore = ((int) Double.parseDouble(app.getBalance()) * 100) * 0.01;

    double cost = 0.0;
    app.SliderIncrease();
    while (balanceBefore >= cost) {
      assertTrue(app.isEnabledPurchase());
      app.SliderIncrease();
      cost = ((int) Double.parseDouble(app.getCurrentCost()) * 100) * 0.01;
    }
    assertFalse(app.isEnabledPurchase());

  }

  @Test
  public void testIncreaseSlider() {

    final String maxSpeed = "Макс";
    final String mbit = "Мбит/сек (макс.)";
    final String kbit = "Кбит/сек (макс.)";

    if (app.getCurrentSpeed().equals(maxSpeed)) {
      app.SliderDecrease();
      app.SliderDecrease();
    }

    int beforeCurrentSpeed = ((int) Double.parseDouble(app.getCurrentSpeed()) * 100);
    int beforeCurrentCost = ((int) Double.parseDouble(app.getCurrentCost()) * 100);
    String beforeFormatSpeed = app.getCurrentSpeedFormat();

    app.SliderIncrease();

    int afterCurrentSpeed = ((int) Double.parseDouble(app.getCurrentSpeed()) * 100);
    int afterCurrentCost = ((int) Double.parseDouble(app.getCurrentCost()) * 100);
    String afterFormatSpeed = app.getCurrentSpeedFormat();

    if ((beforeFormatSpeed.equals(kbit)) && (afterFormatSpeed.equals(mbit))) {
      assertTrue(afterCurrentSpeed < beforeCurrentSpeed);
    } else assertTrue(afterCurrentSpeed > beforeCurrentSpeed);

    assertTrue(afterCurrentCost > beforeCurrentCost);
  }


  @Test
  public void testDecreaseSlider() {
    final String maxSpeed = "Макс";
    final String mbit = "Мбит/сек (макс.)";
    final String kbit = "Кбит/сек (макс.)";
    final String minSpeed = "64";

    if (app.getCurrentSpeed().equals(maxSpeed)) {
      app.SliderDecrease();
    }

    if (app.getCurrentSpeed().equals(minSpeed)) {
      app.SliderIncrease();
    }

    int beforeCurrentSpeed = ((int) Double.parseDouble(app.getCurrentSpeed()) * 100);
    int beforeCurrentCost = ((int) Double.parseDouble(app.getCurrentCost()) * 100);
    String beforeFormatSpeed = app.getCurrentSpeedFormat();

    app.SliderDecrease();

    int afterCurrentSpeed = ((int) Double.parseDouble(app.getCurrentSpeed()) * 100);
    int afterCurrentCost = ((int) Double.parseDouble(app.getCurrentCost()) * 100);
    String afterFormatSpeed = app.getCurrentSpeedFormat();

    if ((beforeFormatSpeed.equals(mbit)) && (afterFormatSpeed.equals(kbit))) {
      assertTrue(afterCurrentSpeed > beforeCurrentSpeed);

    } else assertTrue(afterCurrentSpeed < beforeCurrentSpeed);

    assertTrue(afterCurrentCost < beforeCurrentCost);

  }
}
