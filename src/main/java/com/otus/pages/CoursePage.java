package com.otus.pages;

import com.google.inject.Inject;
import com.otus.components.CourseComponent;
import com.otus.diconfig.GuiceScoped;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CoursePage extends BasePage {

  @Inject
  public CoursePage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public void isCoursePage() {
    CourseComponent courseComponent = new CourseComponent(guiceScoped);
    List<WebElement> list = courseComponent.isCoursePageLocatorsList();
    Assertions.assertFalse(list.isEmpty());
  }
}
