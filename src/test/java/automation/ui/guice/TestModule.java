package automation.ui.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import org.openqa.selenium.WebDriver;


public class TestModule extends AbstractModule
{

    @Override
    protected void configure()
    {
        bind(WebDriver.class).toProvider(WebDriverProvider.class).in(
                Singleton.class);

    }

}