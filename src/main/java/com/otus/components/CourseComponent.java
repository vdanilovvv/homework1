package com.otus.components;

import com.otus.diconfig.GuiceScoped;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CourseComponent extends BaseComponent {
  public CourseComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  @FindBy(css = "h1[class='course-header2__title']")
  private WebElement isCourseOld;

  @FindBy(xpath = "//*[text()='Формат обучения']")
  private WebElement isCourseNew;

  public List<WebElement> isCoursePageLocatorsList() {
    List<WebElement> list = new ArrayList<>();
    list.add(isCourseNew);
    list.add(isCourseOld);
    return list;
  }


}
