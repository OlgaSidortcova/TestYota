package com.sd.ts;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class TestBase {

//  protected static final ApplicationManager applicationManager = new ApplicationManager(BrowserType.FIREFOX);
protected static final ApplicationManager applicationManager = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));
  @BeforeSuite
  public void setUp() throws Exception {
    applicationManager.init();
  }

  @AfterSuite
  public void tearDown() {
    applicationManager.stop();
  }

  public ApplicationManager getApplicationManager() {
    return applicationManager;
  }
}
