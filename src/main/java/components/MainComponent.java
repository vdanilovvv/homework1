package components;

import org.apache.commons.collections4.ListUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.CoursePage;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainComponent extends BaseComponent {

  public MainComponent(WebDriver driver) {
    super(driver);
  }

  @FindBy(css = "div[class^='lessons__new-item-title']")
  private List<WebElement> coursesNames;

  @FindBy(css = "[class='lessons__new-item-start']")
  private List<WebElement> popularCoursesDates;

  @FindBy(css = "div[class='lessons__new-item-courses']+div")
  private List<WebElement> specificCoursesDates;

  public static WebElement clickCourceDate(WebElement webElement) {
    return webElement
        .findElement(By.xpath("ancestor::a"));
  }


  public CoursePage clickPopularCourseByName(String name) {
    WebElement course = coursesNames.stream()
        .filter((webElement) -> webElement.getText().contains(name))
        .findAny()
        .orElseThrow(() -> new RuntimeException("Incorrect Name"));

    Actions actions = new Actions(driver);

    actions
        .sendKeys(Keys.END)
        .moveToElement(course)
        .build()
        .perform();
    course.click();
    return new CoursePage(driver);
  }

  public WebElement sortCources(WebElement webElement, WebElement webElement2, boolean isEarly) {
    if (isEarly) {
      return getDate(webElement.getText(), true) < getDate(webElement2.getText(), true)
          ? webElement : webElement2;
    } else {
      return getDate(webElement.getText(), false) > getDate(webElement2.getText(), false)
          ? webElement : webElement2;
    }
  }

  public CoursePage clickEarlyLateCourse(Boolean isEarly) {
    List<WebElement> list = ListUtils.union(popularCoursesDates, specificCoursesDates);
    WebElement course = list.stream()
        .reduce((webElement, webElement2) -> sortCources(webElement, webElement2, isEarly))
        .orElseThrow(() -> new RuntimeException("Incorrect Date"));

    Actions actions = new Actions(driver);
    actions
        .sendKeys(Keys.END)
        .moveToElement(clickCourceDate(course))
        .build()
        .perform();
    clickCourceDate(course).click();

    return new CoursePage(driver);
  }

  public static Long getDate(String stringDate, boolean needMin) {
    Matcher matcher = Pattern.compile("\\d+\\s+(??????????[??????]|????????????[??????]|????????[????]?|??????????[??????]|????[??????]|????[????][??????]|????????????[????]?|(?:????????|??????|????|??????)[????]????[??????])").matcher(stringDate);
    String dayAndMonth = matcher.find() ? matcher.group() : "";
    if (dayAndMonth.equals("")) {
      if (needMin) {
        return 9999999999999L;
      } else {
        return 1L;
      }
    }
    String day = dayAndMonth.replaceAll("\\D+", "");
    String month = dayAndMonth.replaceAll("[^??-??]", "");
    LocalDateTime date = LocalDateTime.now();

    switch (month) {
      case "????????????":
        date.withMonth(Month.JANUARY.getValue());
        break;
      case "??????????????":
        date.withMonth(Month.FEBRUARY.getValue());
        break;
      case "??????????":
        date.withMonth(Month.MARCH.getValue());
        break;
      case "????????????":
        date.withMonth(Month.APRIL.getValue());
        break;
      case "??????":
        date.withMonth(Month.MAY.getValue());
        break;
      case "????????":
        date.withMonth(Month.JUNE.getValue());
        break;
      case "????????":
        date.withMonth(Month.JULY.getValue());
        break;
      case "??????????????":
        date.withMonth(Month.AUGUST.getValue());
        break;
      case "????????????????":
        date.withMonth(Month.SEPTEMBER.getValue());
        break;
      case "??????????????":
        date.withMonth(Month.OCTOBER.getValue());
        break;
      case "????????????":
        date.withMonth(Month.NOVEMBER.getValue());
        break;
      case "??????????????":
        date.withMonth(Month.DECEMBER.getValue());
        break;
      default:
        throw new RuntimeException("Incorrect Month");
    }
    int dayInt = Integer.parseInt(day);
    date.withDayOfMonth(dayInt);

    matcher = Pattern.compile("\\d+\\s+(????????)").matcher(stringDate);
    String yearString = matcher.find() ? matcher.group() : "";
    if (!yearString.equals("")) {
      int year = Integer.parseInt(yearString.replaceAll("\\D+", ""));
      date.withYear(year);
    }

    return ZonedDateTime.of(date, ZoneId.systemDefault()).toInstant().toEpochMilli();
  }


}
