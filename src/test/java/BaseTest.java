import components.BaseComponent;
import driver.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    FileInputStream fis;
    Properties prop = new Properties();
    InputStreamReader reader = null;
    final String URL = "https://otus.ru";

    @BeforeEach
    public void startUp() throws IOException, InterruptedException {

        fis = new FileInputStream("src/test/resources/properties");
        reader = new InputStreamReader(fis, "UTF-8");
        prop.load(reader);
        driver = DriverFactory.getWebDriver(prop.getProperty("browser").toLowerCase(Locale.ROOT));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(URL);

        BaseComponent bs = new BaseComponent(driver);
        bs.closeDiscount(driver);
        bs.acceptCookies(driver);
        PageFactory.initElements(driver, this);


    }

    @AfterEach
    public void end() {
        if (driver != null) {
            driver.quit();
        }
    }
}
