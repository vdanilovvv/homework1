package com.otus.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.IOException;
import java.util.Locale;


public class DriverFactory {
  public static EventFiringWebDriver getWebDriver(String browserName) throws IOException {
    EventFiringWebDriver eventDriver;
    switch (browserName) {
      case "chrome":
        WebDriverManager.chromedriver().setup();
        eventDriver = new EventFiringWebDriver(new ChromeDriver());
        break;
      case "firefox":
        WebDriverManager.firefoxdriver().setup();
        eventDriver = new EventFiringWebDriver(new FirefoxDriver());
        break;
      case "opera":
        String os = System.getProperty("os").toLowerCase(Locale.ROOT);
        switch (os) {
          case "windows":
            System.setProperty("webdriver.opera.com.otus.driver", "src/test/resources/operadriver_windows.exe");
            break;
          case "linux":
            System.setProperty("webdriver.opera.com.otus.driver", "src/test/resources/operadriver_linux");
            break;
          case "mac":
            System.setProperty("webdriver.opera.com.otus.driver", "src/test/resources/operadriver_mac");
            break;
          default:
            throw new RuntimeException("Incorrect OS name");
        }
        WebDriverManager.operadriver().setup();
        eventDriver = new EventFiringWebDriver(new OperaDriver());
        break;
      default:
        throw new RuntimeException("Incorrect BrowserName");
    }
    eventDriver.register(new DriverOverride());
    return eventDriver;
  }
}