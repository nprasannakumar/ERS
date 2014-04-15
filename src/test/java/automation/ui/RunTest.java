package automation.ui;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(format = { "pretty", "html:target/cucumber-html-report",
		"json-pretty:target/CampaignIds.json" }, features = { "/Users/kishankp9/Documents/testing/ui/src/test/resources/features/validate.feature"}, tags={"@test"})
public class RunTest {

}
