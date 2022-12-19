package com.otus.pages;

import com.otus.diconfig.GuiceScoped;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
  protected WebDriver driver;
  protected GuiceScoped guiceScoped;

  public BasePage(GuiceScoped guiceScoped) {
    this.guiceScoped = guiceScoped;
    this.driver = guiceScoped.driver;
    PageFactory.initElements(driver, this);
  }

  public void open() {
    driver.get(System.getProperty("base.url"));
  }
}
