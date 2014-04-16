package automation.ui.guice;

import com.google.inject.Provider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class WebDriverProvider implements Provider<WebDriver>
{
    @Override
    public WebDriver get()
    {
        if (System.getProperty("browser").equalsIgnoreCase("firefox"))
            return new FirefoxDriver();
        else
        {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.home") + "/chromedriver");
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("user-data-dir=/Users/kishankp9/Library/Application Support/Google/Chrome/Default/Default");
//            options.addArguments("--start-maximized");
//            System.setProperty("webdriver.chrome.driver", "/Users/kishankp9/Downloads/chromedriver");
//            driver = new ChromeDriver(options);
            return new ChromeDriver();
        }

    }
}
