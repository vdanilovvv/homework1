package pages;

import components.CourseComponent;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CoursePage extends BasePage {

  public CoursePage(WebDriver driver) {
    super(driver);
  }

  public void isCoursePage() {
    CourseComponent courseComponent = new CourseComponent(driver);
    List<WebElement> list = courseComponent.isCoursePageLocatorsList();
    Assertions.assertFalse(list.isEmpty());
  }
}
