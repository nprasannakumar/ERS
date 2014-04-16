package automation.ui.cukes;

import automation.ui.pages.KayakPage;
import automation.ui.pages.KayakResultsPage;
import com.google.inject.Inject;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;


public class KayakStepDefs
{

    private WebDriver driver;
    private KayakPage kayakPage;
    private KayakResultsPage kayakResultsPage;
    private static final String url = "http://www.kayak.com/";


    @Inject
    public KayakStepDefs(WebDriver driver)
    {
        this.driver = driver;

    }

    @Given("^I am in the site to check flight fares$")
    public void I_am_in_the_site_to_calculate_the_square_root_of_a_number() throws Throwable
    {
        driver.get(url);
        kayakPage = new KayakPage(driver);
        kayakResultsPage = new KayakResultsPage(driver);
    }

	/*

	@When("^I enter the source \"([^\"]*)\" and destination \"([^\"]*)\" cities$")
	public void I_enter_the_source_and_destination_cities(String source, String dest) throws Throwable {
	    // Express the Regexp above with the code you wish you had
		kayakPage.chooseCities(source, dest);
	    
	}
	*/
  /*
	@When("^I enter the source \"([^\"]*)\" and destination \"([^\"]*)\" cities and select the start date \"([^\"]*)\" and end date \"([^\"]*)\"$")
	public void I_enter_the_source_and_destination_cities_and_select_the_start_date_and_end_date(String source, String dest, String startDate, String endDate) throws Throwable {
		
		kayakPage.chooseCities(source, dest, startDate, endDate);
	}
	*/

    @When("^I enter the source \"([^\"]*)\" and destination \"([^\"]*)\" cities and select the start date \"([^\"]*)\" and end date \"([^\"]*)\"$")
    public void I_enter_the_source_and_destination_cities_and_select_the_start_date_and_end_date(String source, String dest, String startDate, String endDate) throws Throwable
    {
        String[] startDateWithMonth = startDate.split("\\s+");
        String startMonth = startDateWithMonth[0];
        String departureDate = startDateWithMonth[1];
        String[] endDateWithMonth = endDate.split("\\s+");
        String endMonth = endDateWithMonth[0];
        String arrivalDate = endDateWithMonth[1];
        kayakPage.chooseCities(source, dest, departureDate, arrivalDate);
    }

    @Then("^I verify the search results$")
    public void I_verify_search_results() throws InterruptedException
    {
        kayakResultsPage.verify_results();
    }


}
