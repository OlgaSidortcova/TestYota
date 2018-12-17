package com.sd.ts;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class ApplicationManager {
  WebDriver wd;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public static boolean isAlertPresent(WebDriver wd) {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public void init() {

    if (browser.equals(BrowserType.FIREFOX)) {
      wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary("/home/sadsido/Tools/firefox/firefox"));
    } else if (browser.equals(BrowserType.CHROME)) {
      wd = new ChromeDriver();

    }
    wd.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
    wd.get("http://localhost:4567/index.html");
  }


  public void Reset() {
    wd.findElement(By.xpath("//div/div[1]/div/div/div[1]/div[2]/a[2]")).click();
    wd.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
  }

  public void Purchase() {
    wd.findElement(By.xpath("//div/div[2]/div[3]/div[2]/div/div/div/a")).click();
    wd.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
  }

  public boolean isEnabledPurchase() {
    wd.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
    WebElement button = wd.findElement(By.xpath("//div/div[2]/div[3]/div[2]/div/div/div/a"));
    String classes = button.getAttribute("class");
    return !classes.contains("disabled");
  }

  public void IncreaseBalance() {
    wd.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
    wd.findElement(By.xpath("//div[@class='actions']/a[1]")).click();
  }

  public String getBalance() {
    wd.manage().timeouts().implicitlyWait(400, TimeUnit.SECONDS);
    return wd.findElement(By.xpath("//dd[@id='balance-holder']//span")).getText();
  }

  public String getCurrentCost() {
    return wd.findElement(By.xpath("//div/div[2]/div[3]/div[2]/div/div/div/div[3]/strong")).getText();
  }

  public String getCurrentSpeed() {
    return wd.findElement(By.xpath("//div/div[2]/div[3]/div[2]/div/div/div/div[2]/strong")).getText();
  }

  public String getCurrentSpeedFormat() {
    return wd.findElement(By.xpath("//div/div[2]/div[3]/div[2]/div/div/div/div[2]/span")).getText();
  }

  public String getCostNoArrow() {
    return wd.findElement(By.xpath("//div/div[2]/div[1]/div/div[2]/div/div/div[3]/strong")).getText();
  }

  public String getSpeed() {
    return wd.findElement(By.xpath("//div/div[2]/div[1]/div/div[2]/div/div/div[2]/strong")).getText();
  }

  public void SetBalance(double balance) {
    wd.findElement(By.id("amount")).click();
    wd.findElement(By.id("amount")).clear();
    wd.findElement(By.id("amount")).sendKeys(Double.toString(balance));
  }

  public void SliderIncrease() {
    wd.manage().timeouts().implicitlyWait(1200, TimeUnit.SECONDS);
    wd.findElement(By.xpath("//div[@class='increase']/a[1]")).click();
  }

  public void SliderDecrease() {
    wd.manage().timeouts().implicitlyWait(1200, TimeUnit.SECONDS);
    wd.findElement(By.xpath("//div[@class='decrease']/a[1]")).click();
  }

  public void stop() {
    wd.quit();
  }
}