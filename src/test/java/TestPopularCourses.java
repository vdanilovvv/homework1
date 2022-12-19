import com.otus.annotations.Driver;
import com.otus.components.MainComponent;
import com.otus.extensions.SeleniumExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import com.otus.pages.MainPage;

@ExtendWith(SeleniumExtension.class)
public class TestPopularCourses {

  @Driver
  private WebDriver driver;

//  @Test
//  @Disabled
//  public void testClickEarlyCourse() {
//    new MainPage(driver).open();
//
//    new MainComponent(driver)
//        .clickEarlyLateCourse(true)
//        .isCoursePage();
//  }
//
//  @Test
//  @Disabled
//  public void testClickLateCourse() {
//    new MainPage(driver).open();
//
//    new MainComponent(driver)
//        .clickEarlyLateCourse(false)
//        .isCoursePage();
//  }
//
//  @Test
//  @Disabled
//  public void testClickCourseName() {
//    new MainPage(driver).open();
//
//    new MainComponent(driver)
//        .clickPopularCourseByName(System.getProperty("courseName"))
//        .isCoursePage();
//  }
}
