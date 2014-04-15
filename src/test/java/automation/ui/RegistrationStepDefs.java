package automation.ui;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RegistrationStepDefs {

	private WebDriver driver;
	private GooglePage googlePage;
	private FormResponsePage formResponsePage;
	
	public RegistrationStepDefs(){
		System.setProperty("webdriver.chrome.driver", "/Users/kishankp9/Downloads/chromedriver");
		driver = new ChromeDriver();
	}
	
	@Given("^I open link \"([^\"]*)\"$")
	public void I_open_the_link(String url){
		driver.get(url);
		googlePage = new GooglePage(driver);
	}
	
	@When("^I fill in the first name \"([^\"]*)\"$")
	public void I_fill_in_my_first_name(String firstName) throws Throwable{
		googlePage.fill_in_details(firstName);
	}
	
	@When("^I fill in a very long first name$")
	public void I_fill_in_a_very_long_first_name() throws Exception{
		String firstName = RandomStringUtils.randomAlphanumeric(50);
		System.out.println(firstName);
		googlePage.fill_in_details(firstName);
	}
	
	@When("^I fill in the first name \"([^\"]*)\", last name \"([^\"]*)\"$")
	public void I_fill_first_and_last_name(String firstName, String lastName){
		googlePage.fill_in_firstAndLast(firstName, lastName);
		
	}
	
	@Then("^the form should be submitted successfully and no error message should be seen$")
	public void the_form_submitted_successfully(){
		 googlePage.form_submitted_successfully();
	}
	
	@Then("^an error message should be shown if the first name is empty$")
	public void the_form_throws_error_when_empty(){
		googlePage.form_throws_error_on_empty();
	}
	
	@Then("^an error message should be shown if the first name is invalid$")
	public void the_form_throws_error_when_invalid(){
		googlePage.form_throws_error_when_invalid();
	}
	
	@Then("^I close the browser$")
	public void I_close_the_browser() throws Throwable {
		driver.quit();
	}
}
