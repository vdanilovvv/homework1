package com.otus.steps.components;

import com.google.inject.Inject;
import com.otus.components.MainComponent;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;

public class MainComponentSteps {

  @Inject
  private MainComponent mainComponent;

  @Если("Кликнуть на самый ранний курс на странице")
  public void clickEarlyCourse(){
    mainComponent.clickEarlyLateCourse(true);
  }

  @Если("Кликнуть на самый поздний курс на странице")
  public void clickLateCourse(){
    mainComponent.clickEarlyLateCourse(false);
  }

  @Если("Кликнуть курс с названием {string} на странице")
  public void clickCourseName(String courseName){
    mainComponent.clickPopularCourseByName(courseName);
  }
}
