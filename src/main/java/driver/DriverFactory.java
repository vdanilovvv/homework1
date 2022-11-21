package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;


public class DriverFactory {
    static FileInputStream fis;
    static Properties prop = new Properties();


    public static EventFiringWebDriver getWebDriver(String browserName) throws IOException {
        EventFiringWebDriver eventDriver;
        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                eventDriver = new EventFiringWebDriver(new ChromeDriver());
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                eventDriver = new EventFiringWebDriver(new FirefoxDriver());
                break;
            case "opera":
                fis = new FileInputStream("src/test/resources/properties");
                prop.load(fis);
                String os = prop.getProperty("os").toLowerCase(Locale.ROOT);
                switch (os) {
                    case "windows":
                        System.setProperty("webdriver.opera.driver", "src/test/resources/operadriver_windows.exe");
                        break;
                    case "linux":
                        System.setProperty("webdriver.opera.driver", "src/test/resources/operadriver_linux");
                        break;
                    case "mac":
                        System.setProperty("webdriver.opera.driver", "src/test/resources/operadriver_mac");
                        break;
                    default:
                        throw new RuntimeException("Incorrect OS name");
                }
                WebDriverManager.operadriver().setup();
                eventDriver = new EventFiringWebDriver(new OperaDriver());
                break;
            default:
                throw new RuntimeException("Incorrect BrowserName");
        }
        eventDriver.register(new DriverOverride());
        return eventDriver;
    }
}