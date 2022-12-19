package com.otus.steps.pages;

import com.google.inject.Inject;
import com.otus.pages.CoursePage;
import io.cucumber.java.ru.Тогда;

public class CoursePageSteps {

  @Inject
  private CoursePage coursePage;

  @Тогда("Откроется страница курса")
  public void checkCoursePage(){
    coursePage.isCoursePage();
  }
}
