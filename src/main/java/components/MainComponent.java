package components;

import org.apache.commons.collections4.ListUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.CoursePage;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainComponent extends BaseComponent {

    public MainComponent(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div[class^='lessons__new-item-title']")
    public static List<WebElement> coursesNames;

    @FindBy(css = "[class='lessons__new-item-start']")
    public static List<WebElement> popularCoursesDates;

    @FindBy(css = "div[class='lessons__new-item-courses']+div")
    public static List<WebElement> specificCoursesDates;


    public CoursePage clickPopularCourseByName(String name){
        WebElement course = coursesNames.stream()
                .filter((webElement) -> webElement.getText().contains(name))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Incorrect Name"));

        System.out.println(course.getText() + "ИМЯ");
        course.click();
        return new CoursePage(driver);
    }


    public CoursePage clickEarlyCourse() {
        List<WebElement> list = ListUtils.union(popularCoursesDates, specificCoursesDates);
        WebElement course = list.stream()
                .peek(webElement -> System.out.println(getDate(webElement.getText(),true)))
                .reduce((webElement, webElement2) ->
                        getDate(webElement.getText(), true) < getDate(webElement2.getText(), true) ?
                                webElement : webElement2)
                .orElseThrow(() -> new RuntimeException("Incorrect Date"));

        System.out.println(course.getText() + "СТАРОЕ");
        Actions actions = new Actions(driver);
        actions
                .moveToElement(course)
                .click()
                .build()
                .perform();
        return new CoursePage(driver);
    }

    public CoursePage clickLateCourse() {
        List<WebElement> list = ListUtils.union(popularCoursesDates, specificCoursesDates);
        WebElement course = list.stream()
                .peek(webElement -> System.out.println(getDate(webElement.getText(),false)))
                .reduce((webElement, webElement2) ->
                        getDate(webElement.getText(), false) > getDate(webElement2.getText(), false) ?
                                webElement : webElement2)
                .orElseThrow(() -> new RuntimeException("Incorrect Date"));
        System.out.println(course.getText() + "НОВОЕ");
        course.click();
        //        Actions actions = new Actions(driver);
//        actions
//                .moveToElement(course)
//                .click()
//                .build()
//                .perform();
        return new CoursePage(driver);

    }

    public static Long getDate(String stringDate, boolean needMin) {
        Matcher matcher = Pattern.compile("\\d+\\s+(январ[ьея]|феврал[ьея]|март[еа]?|апрел[ьея]|ма[йея]|ию[нл][яье]|август[еа]?|(?:сент|окт|но|дек)[ая]бр[яье])").matcher(stringDate);
        String dayAndMonth = matcher.find() ? matcher.group() : "";
        if (dayAndMonth.equals("")) {
            if (needMin) {
                return 9991763231049L;
            } else {
                return 1L;
            }
        }
        String day = dayAndMonth.replaceAll("\\D+", "");
        String month = dayAndMonth.replaceAll("[^а-я]", "");
        Date date = new Date();

        switch (month) {
            case "января":
                date.setMonth(Calendar.JANUARY);
                break;
            case "февраля":
                date.setMonth(Calendar.FEBRUARY);
                break;
            case "марта":
                date.setMonth(Calendar.MARCH);
                break;
            case "апреля":
                date.setMonth(Calendar.APRIL);
                break;
            case "мая":
                date.setMonth(Calendar.MAY);
                break;
            case "июня":
                date.setMonth(Calendar.JUNE);
                break;
            case "июля":
                date.setMonth(Calendar.JULY);
                break;
            case "августа":
                date.setMonth(Calendar.AUGUST);
                break;
            case "сентября":
                date.setMonth(Calendar.SEPTEMBER);
                break;
            case "октября":
                date.setMonth(Calendar.OCTOBER);
                break;
            case "ноября":
                date.setMonth(Calendar.NOVEMBER);
                break;
            case "декабря":
                date.setMonth(Calendar.DECEMBER);
                break;
        }
        int dayInt = Integer.parseInt(day);
        date.setDate(dayInt);
        Date currentDate = new Date();
        if (date.getTime() < currentDate.getTime()) {
            date.setYear(date.getYear() + 1);
        }
        return date.getTime();
    }


}
