package automation.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class KayakStepDefs {
	
	private WebDriver driver;
	private KayakPage kayakPage;
	private KayakResultsPage kayakResultsPage;
	private static final String url = "http://www.kayak.com/";
	
	
	public KayakStepDefs(){
		if(System.getProperty("browser").equals("firefox")){
			driver = new FirefoxDriver();
		}
		else{
			ChromeOptions options = new ChromeOptions();
			options.addArguments("user-data-dir=/Users/kishankp9/Library/Application Support/Google/Chrome/Default/Default");
			options.addArguments("--start-maximized");
			System.setProperty("webdriver.chrome.driver", "/Users/kishankp9/Downloads/chromedriver");
			driver = new ChromeDriver(options);
		}
		
	}
	
	@Given("^I am in the site to check flight fares$")
	public void I_am_in_the_site_to_calculate_the_square_root_of_a_number() throws Throwable {
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
	public void I_enter_the_source_and_destination_cities_and_select_the_start_date_and_end_date(String source, String dest, String startDate, String endDate) throws Throwable {
		String[] startDateWithMonth = startDate.split("\\s+");
		String startMonth = startDateWithMonth[0];
		String departureDate = startDateWithMonth[1];
		String[] endDateWithMonth = endDate.split("\\s+");
		String endMonth = endDateWithMonth[0];
		String arrivalDate = endDateWithMonth[1];
		kayakPage.chooseCities(source, dest, departureDate, arrivalDate);
	}
	
	@Then("^I verify the search results$")
	public void I_verify_search_results() throws InterruptedException{
		kayakResultsPage.verify_results();
	}

	@After()
	public void close_browser(){
		driver.close();
	}
}
