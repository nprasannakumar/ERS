package automation.ui.cukes;


import java.util.Random;

import automation.ui.pages.FormPage;
import com.google.inject.Inject;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;


public class FormStepDefs
{

    private WebDriver driver;
    private FormPage formPage;
    Random rand = new Random();
    int lengthRandomlyGenerated = 0;
    int domainPart = 250;

    private static final String url = "https://docs.google.com/forms/d/1HUzLL8WAFjDJOFN650rtB15JJGBbfZwQCs-OfjhpZew/viewform";

    @Inject
    public FormStepDefs(WebDriver driver)
    {
        this.driver = driver;
    }


    @Given("^I am in the form page$")
    public void I_am_in_the_form_page()
    {

        driver.get(url);
        formPage = new FormPage(driver);
    }


    @When("^I enter incorrect value only to field \"([^\"]*)\" and value \"([^\"]*)\"$")
    public void I_enter_incorrect_value_only_to_field_and_value(String fieldName, String value) throws Throwable
    {
        System.out.println("fieldname :" + fieldName);
        System.out.println("value:" + value);
        System.out.println(formPage);
        formPage.verifyField(fieldName, value);
    }


    @When("^I enter strings in field \"([^\"]*)\" greater than the allowed length (\\d+)$")
    public void I_enter_strings_in_field_greater_than_the_allowed_length(String fieldName, int length) throws Throwable
    {
        lengthRandomlyGenerated = length + rand.nextInt(length);
        domainPart = domainPart + rand.nextInt(length);
        String endOfDomain = RandomStringUtils.randomAlphabetic(lengthRandomlyGenerated);
        String subsetOfString = endOfDomain.substring(0, 4);
        System.out.println("com part is:" + subsetOfString);


        System.out.println("randomly generated number is:" + lengthRandomlyGenerated);

        if (fieldName.equals("firstName") || fieldName.equals("lastName"))
        {

            String value = RandomStringUtils.randomAlphabetic(lengthRandomlyGenerated);
            formPage.verifyField(fieldName, value);
        }
        else if (fieldName.equals("phoneNumber"))
        {
            String value = RandomStringUtils.randomNumeric(lengthRandomlyGenerated);
            formPage.verifyField(fieldName, value);
        }
        else if (fieldName.equals("email"))
        {
            String domain = RandomStringUtils.randomAlphanumeric(domainPart).toLowerCase();
            System.out.println("domain length:" + domain.length());

            String val = RandomStringUtils.randomAlphanumeric(lengthRandomlyGenerated) + "@" + RandomStringUtils.randomAlphanumeric(domainPart).toLowerCase() + "." + subsetOfString;
            System.out.println("email is:" + val);
            System.out.println("email length is:" + val.length());
            formPage.verifyField(fieldName, val);
        }
    }


    @When("^I leave the \"([^\"]*)\" as empty and enter suffix as \"([^\"]*)\" firstName as \"([^\"]*)\", lastName as \"([^\"]*)\", phoneNumber as \"([^\"]*)\", emailId as \"([^\"]*)\", dob as \"([^\"]*)\" and gender as \"([^\"]*)\"$")
    public void I_leave_the_as_empty_and_enter_suffix_as_firstName_as_lastName_as_phoneNumber_as_emailId_as_dob_as_and_gender_as(String missingField,
                                                                                                                                 String suffix,
                                                                                                                                 String firstName,
                                                                                                                                 String lastName,
                                                                                                                                 String phoneNumber,
                                                                                                                                 String email,
                                                                                                                                 String date,
                                                                                                                                 String gender) throws Throwable
    {

        formPage.verify_for_empty(missingField, suffix, firstName, lastName, phoneNumber, email, date, gender);

    }


    @When("^I enter valid value to fields suffix \"([^\"]*)\", firstName \"([^\"]*)\", lastName \"([^\"]*)\", phoneNumber \"([^\"]*)\", emailId \"([^\"]*)\", date \"([^\"]*)\", gender \"([^\"]*)\" and submit the form$")
    public void I_enter_valid_value_to_fields_suffix_firstName(String suffix, String firstName, String lastName, String phoneNumber, String email, String date, String gender) throws Throwable
    {
        formPage.check_for_all_fields(suffix, firstName, lastName, phoneNumber, email, date, gender);
    }


    @When("^I enter related values to fields suffix \"([^\"]*)\" and gender \"([^\"]*)\" and valid values to fields firstName \"([^\"]*)\", lastName \"([^\"]*)\", phoneNo \"([^\"]*)\", email \"([^\"]*)\", date \"([^\"]*)\" and submit the form$")
    public void I_enter_related_values_to_fields_suffix_and_gender_and_valid_values_to_fields_firstName_lastName_phoneNo_email_date_and_submit_the_form(String suffix,
                                                                                                                                                        String gender,
                                                                                                                                                        String firstName,
                                                                                                                                                        String lastName,
                                                                                                                                                        String phoneNumber,
                                                                                                                                                        String email,
                                                                                                                                                        String date) throws Throwable
    {
        formPage.check_for_all_fields(suffix, firstName, lastName, phoneNumber, email, date, gender);
    }

    @Then("^the form should not be submitted succesfully$")
    public void the_form_should_not_be_submitted_succesfully() throws Throwable
    {
        System.out.println("the current url is:" + driver.getCurrentUrl());
        String responseUrl = driver.getCurrentUrl();
        formPage.form_should_not_be_submitted(responseUrl);
    }

    @Then("^I get the error \"([^\"]*)\"$")
    public void I_get_the_error(String errorMessage) throws Throwable
    {
        formPage.check_error_message(errorMessage);
    }

    @Then("^I get the required message error as \"([^\"]*)\"$")
    public void I_get_the_required_message_error_as(String requiredMessage) throws Throwable
    {
        formPage.check_required_message(requiredMessage);
    }


    @Then("^the form is submitted successfully and I get the success message \"([^\"]*)\"$")
    public void the_form_is_submitted_successfully_and_I_get_the_success_message(String successMessage) throws Throwable
    {
        formPage.form_submitted_successfully(successMessage);
    }


}
