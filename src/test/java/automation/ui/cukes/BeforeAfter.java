package automation.ui.cukes;

import com.google.inject.Inject;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class BeforeAfter
{
    private WebDriver driver;

    @Inject
    public BeforeAfter(WebDriver driver)
    {
        this.driver = driver;
    }

    @After()
    public void tearDown(Scenario scenario) throws Throwable
    {
        if (scenario.isFailed())
        {
            byte[] screenShot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenShot, "image/png");
        }
        driver.quit();
    }

}
