import components.MainComponent;
import org.junit.jupiter.api.Test;

public class TestPopularCourses extends BaseTest {

    @Test
    public void testClickEarlyCourse() {
        new MainComponent(driver)
                .clickEarlyCourse();

    }

    @Test
    public void testClickLateCourse() {
        new MainComponent(driver)
                .clickLateCourse();
    }

    @Test
    public void testClickCourseName() {
        String name = prop.getProperty("courseName");
        new MainComponent(driver)
                .clickPopularCourseByName(name);
    }
}
