package automation.ui.cukes;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@Cucumber.Options(format = {"pretty", "html:target/cucumber-html-report",
        "json:target/CampaignIds.json"}, features = {"classpath:features/validate.feature"}, tags = {"@test"})
public class RunTest
{

}
