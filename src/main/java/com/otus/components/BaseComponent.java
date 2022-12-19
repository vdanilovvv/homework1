package com.otus.components;

import com.otus.diconfig.GuiceScoped;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.otus.pages.BasePage;

public abstract class BaseComponent extends BasePage {

  public BaseComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
    PageFactory.initElements(driver, this);
  }


}
