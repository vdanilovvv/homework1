import annotations.Driver;
import components.MainComponent;
import extensions.SeleniumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

@ExtendWith(SeleniumExtension.class)
public class TestPopularCourses {

  @Driver
  private WebDriver driver;

  @Test
  public void testClickEarlyCourse() {
    new MainPage(driver).open();

    new MainComponent(driver)
        .clickEarlyLateCourse(true)
        .isCoursePage();
  }

  @Test
  public void testClickLateCourse() {
    new MainPage(driver).open();

    new MainComponent(driver)
        .clickEarlyLateCourse(false)
        .isCoursePage();
  }

  @Test
  public void testClickCourseName() {
    new MainPage(driver).open();

    new MainComponent(driver)
        .clickPopularCourseByName(System.getProperty("courseName"))
        .isCoursePage();
  }
}
