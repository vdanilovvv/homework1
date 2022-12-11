package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public abstract class BaseComponent extends BasePage {

  public BaseComponent(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }


}
