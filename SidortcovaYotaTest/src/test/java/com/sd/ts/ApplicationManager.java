package com.sd.ts;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class ApplicationManager {

  // private final Properties properties;
  //FirefoxDriver wd;

  WebDriver wd;

  // private SessionHelper sessionHelper;
  private String browser;

  public ApplicationManager(String browser) {

    this.browser = browser;
    //properties = new Properties();
  }

  public static boolean isAlertPresent(WebDriver wd) {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public void init() throws IOException {

    // String browser = BrowserType.FIREFOX;

    // String target = System.getProperty("target", "local");
    //  properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    // dbHelper = new DbHelper();

    // if ("".equals(properties.getProperty("selenium.server"))) {

    if (browser.equals(BrowserType.FIREFOX)) {
      wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary("/home/sadsido/Tools/firefox/firefox"));
    } else if (browser.equals(BrowserType.CHROME)) {
      wd = new ChromeDriver();
    }
    //  } else {
    //   DesiredCapabilities capabilities = new DesiredCapabilities();
    //   capabilities.setBrowserName(browser);
    //   wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
    //  }

    wd.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
    //   wd.get(properties.getProperty("web.baseUrl"));
    wd.get("http://localhost:4567/index.html");

    // sessionHelper = new SessionHelper(wd);


    // sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));

  }
/*
  protected void init() {
    wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary("/home/sadsido/Tools/firefox/firefox"));
    wd.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
    wd.get("http://localhost:4567/index.html");
  }
*/

  public void Reset() {
    wd.findElement(By.xpath("//div/div[1]/div/div/div[1]/div[2]/a[2]")).click();
  }

  public void Purchase() {

    wd.findElement(By.xpath("//div/div[2]/div[3]/div[2]/div/div/div/a")).click();
  }

  public void IncreaseBalance() {

    wd.findElement(By.xpath("//div[@class='actions']/a[1]")).click();
  }


  public String getBalance() {
    wd.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
    return wd.findElement(By.xpath("//dd[@id='balance-holder']//span")).getText();
  }


  public String getCurrentCost() {
    return wd.findElement(By.xpath("//div/div[2]/div[3]/div[2]/div/div/div/div[3]/strong")).getText();
  }

  public String getCurrentSpeed() {
    return wd.findElement(By.xpath("//div/div[2]/div[3]/div[2]/div/div/div/div[2]/strong")).getText();//ok
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
    wd.findElement(By.xpath("//div[@class='increase']/a")).click();
  }

  public void stop() {
    wd.quit();
  }


}