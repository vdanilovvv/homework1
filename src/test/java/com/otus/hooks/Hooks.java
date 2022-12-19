package com.otus.hooks;

import com.google.inject.Inject;
import com.otus.diconfig.GuiceScoped;
import io.cucumber.java.After;

public class Hooks {
  @Inject
  private GuiceScoped guiceScoped;

  @After
  public void afterTest() {
    if (guiceScoped.driver != null) {
      guiceScoped.driver.quit();
    }
  }
}
