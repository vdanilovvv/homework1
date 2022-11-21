package components;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

public class BaseComponent extends BasePage {

    public BaseComponent(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "button[class='js-cookie-accept cookies__button']")
    private WebElement acceptCookiesButton;

    public BasePage acceptCookies(WebDriver driver) {
        WebDriverWait waitDriver = new WebDriverWait(driver, 10);
        if (waitDriver.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton)).isDisplayed()){
            Dimension size = acceptCookiesButton.getSize();
            Actions actions = new Actions(driver);
            actions
                    .moveToElement(acceptCookiesButton)
                    .moveByOffset(size.getWidth()/2,0)
                    .click()
                    .build()
                    .perform();
//            acceptCookiesButton.click();

        }
        return new BasePage(driver);

    }

    @FindBy(css = "div[class='sticky-banner__close js-sticky-banner-close']")
    private WebElement closeDiscountButton;

    public BasePage closeDiscount(WebDriver driver) {
        if (closeDiscountButton.isEnabled()) {
            closeDiscountButton.click();
        }
        return new BasePage(driver);
    }

}
