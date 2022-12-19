package com.otus.pages;

import com.google.inject.Inject;
import com.otus.diconfig.GuiceScoped;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

  @Inject
  public MainPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }


}
