package com.otus.diconfig;

import com.otus.driver.DriverFactory;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Locale;

@ScenarioScoped
public class GuiceScoped {
  public final WebDriver driver = DriverFactory.getWebDriver(System.getProperty("browser").toLowerCase(Locale.ROOT));

  public GuiceScoped() throws IOException {
  }
}
