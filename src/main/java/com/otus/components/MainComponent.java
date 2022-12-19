package com.otus.components;

import com.google.inject.Inject;
import com.otus.diconfig.GuiceScoped;
import org.apache.commons.collections4.ListUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import com.otus.pages.CoursePage;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainComponent extends BaseComponent {

  @Inject
  public MainComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
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


  public void clickPopularCourseByName(String name) {
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

  public void clickEarlyLateCourse(Boolean isEarly) {
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
  }

  public static Long getDate(String stringDate, boolean needMin) {
    Matcher matcher = Pattern.compile("\\d+\\s+(январ[ьея]|феврал[ьея]|март[еа]?|апрел[ьея]|ма[йея]|ию[нл][яье]|август[еа]?|(?:сент|окт|но|дек)[ая]бр[яье])").matcher(stringDate);
    String dayAndMonth = matcher.find() ? matcher.group() : "";
    if (dayAndMonth.equals("")) {
      if (needMin) {
        return 9999999999999L;
      } else {
        return 1L;
      }
    }
    String day = dayAndMonth.replaceAll("\\D+", "");
    String month = dayAndMonth.replaceAll("[^а-я]", "");
    LocalDateTime date = LocalDateTime.now();

    switch (month) {
      case "января":
        date.withMonth(Month.JANUARY.getValue());
        break;
      case "февраля":
        date.withMonth(Month.FEBRUARY.getValue());
        break;
      case "марта":
        date.withMonth(Month.MARCH.getValue());
        break;
      case "апреля":
        date.withMonth(Month.APRIL.getValue());
        break;
      case "мая":
        date.withMonth(Month.MAY.getValue());
        break;
      case "июня":
        date.withMonth(Month.JUNE.getValue());
        break;
      case "июля":
        date.withMonth(Month.JULY.getValue());
        break;
      case "августа":
        date.withMonth(Month.AUGUST.getValue());
        break;
      case "сентября":
        date.withMonth(Month.SEPTEMBER.getValue());
        break;
      case "октября":
        date.withMonth(Month.OCTOBER.getValue());
        break;
      case "ноября":
        date.withMonth(Month.NOVEMBER.getValue());
        break;
      case "декабря":
        date.withMonth(Month.DECEMBER.getValue());
        break;
      default:
        throw new RuntimeException("Incorrect Month");
    }
    int dayInt = Integer.parseInt(day);
    date.withDayOfMonth(dayInt);

    matcher = Pattern.compile("\\d+\\s+(года)").matcher(stringDate);
    String yearString = matcher.find() ? matcher.group() : "";
    if (!yearString.equals("")) {
      int year = Integer.parseInt(yearString.replaceAll("\\D+", ""));
      date.withYear(year);
    }

    return ZonedDateTime.of(date, ZoneId.systemDefault()).toInstant().toEpochMilli();
  }


}
