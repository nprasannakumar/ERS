package automation.ui.cukes;

import automation.ui.pages.SquareRootCalculatorPage;
import com.google.inject.Inject;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;


public class SquareRootStepDefs
{

    private double sqrtToCalculate = 0.0;

    private WebDriver driver;
    private SquareRootCalculatorPage squareRootCalculatorPage;

    private static final String url = "http://www.cj-design.com/demos/cjfloatingcalculatorv1/index.html";

    @Inject
    public SquareRootStepDefs(WebDriver driver)
    {
        this.driver = driver;
    }


    @Given("^I am in the site to calculate the square root of a number$")
    public void I_am_in_the_site_to_calculate_the_square_root_of_a_number() throws Throwable
    {
        driver.get(url);
        squareRootCalculatorPage = new SquareRootCalculatorPage(driver);

    }

	/*
  @When("^I enter an incorrect value @@ in the number field and calculate the square root$")
	public void I_enter_an_incorrect_value_in_the_number_field_and_calculate_the_square_root() throws Throwable {
	    // Express the Regexp above with the code you wish you had
	}
    */

	/*
  @When("^I enter an incorrect value (\\d+) in the number field and calculate the square root$")
	public void I_enter_an_incorrect_value_in_the_number_field_and_calculate_the_square_root(int arg1) throws Throwable {
	    // Express the Regexp above with the code you wish you had
	    
	}
	*/

    @When("^I enter an incorrect value \"([^\"]*)\" in the number field and calculate the square root$")
    public void I_enter_an_incorrect_value_in_the_number_field_and_calculate_the_square_root(String number) throws Throwable
    {
        // Express the Regexp above with the code you wish you had
        squareRootCalculatorPage.find_square_root(number);
    }


    @Then("^I get an error Value \"([^\"]*)\"$")
    public void I_get_an_error_Value(String errorValue) throws Throwable
    {
        squareRootCalculatorPage.check_error_message(errorValue);
    }

    @When("^I enter a valid value \"([^\"]*)\" in the number field and calculate its square root$")
    public void I_enter_a_valid_value_in_the_number_field_and_calculate_its_square_root(String number) throws Throwable
    {
        //sqrtToCalculate = Math.sqrt(Double.parseDouble(number));
        squareRootCalculatorPage.find_square_root(number);
    }


    @Then("^I get the square root of the number \"([^\"]*)\"$")
    public void I_get_the_square_root_of_the_number(String number) throws Throwable
    {
        sqrtToCalculate = Math.sqrt(Double.parseDouble(number));
        squareRootCalculatorPage.check_sqrt_of_number(sqrtToCalculate);
    }
}
