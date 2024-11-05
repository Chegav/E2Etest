package com.framework.base;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.framework.config.BrowserType;
import com.framework.config.Settings;

import io.cucumber.java.Scenario;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
public class FrameworkInitialize extends Base {
  public static void InitializeBrowser(BrowserType browserType, Scenario scenario) throws MalformedURLException {
    RemoteWebDriver driver = null;
    String osFound = System.getProperty("os.name").toLowerCase();
    System.out.println("================================================================================");
    System.out.println(" Environment : " + Settings.Environment);
    System.out.println(" Name Scenario : " + scenario.getName());
    System.out.println(" Operative System : " + osFound);
    System.out.println(" Browser : " + Settings.BrowserType);
    System.out.println("========================================");
    System.out.println("Start at " + new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
    boolean noHeadless = System.getProperty("noHeadless") != null && Boolean.parseBoolean(System.getProperty("noHeadless"));
    System.out.println("No headless:  " + noHeadless);
    switch (browserType) {
      case Chrome: {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (!noHeadless) { options.addArguments("--headless"); };
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        break;
      }
      case Firefox: {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        if (noHeadless) { options.addArguments("--headless"); };
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new FirefoxDriver(options);
        break;
      }
      case Edge: {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        if (noHeadless) { options.addArguments("--headless"); };
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new EdgeDriver(options);
        break;
      }
      case Safari: {
        break;
      }
    }
    driver.manage().window().maximize();
    Dimension dimension = new Dimension(1366, 800);
    driver.manage().window().setSize(dimension);
    System.out.println("Width: " + driver.manage().window().getSize().getWidth());
    System.out.println("Height: " + driver.manage().window().getSize().getHeight());
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    LocalDriverContext.setRemoteWebDriverThreadLocal(driver);
  }
}
