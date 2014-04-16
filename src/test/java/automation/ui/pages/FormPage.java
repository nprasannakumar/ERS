package automation.ui.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FormPage extends RootClass {
	
	@FindBy(id="entry_27481262")
	private WebElement suffixElement;
	
	@FindBy(id="entry_715098420")
	private WebElement firstNameElement;
	
	
	@FindBy(id="entry_1565250029")
	private WebElement lastNameElement;
	
	@FindBy(css="div[class='error-message']")
	private List<WebElement> errorMessageElement;
	
	
	@FindBy(id="entry_602977061")
	private WebElement phoneNumberElement;
	
	@FindBy(id="entry_1418949405")
	private WebElement emailElement;
	
	@FindBy(css="div[class='ss-form-entry']")
	private WebElement formElement;
	
	@FindBy(css="input[class*='ss-q-short error']")
	private WebElement findErrorElement;
	
	@FindBy(id="entry_437979468")
	private WebElement dateElement;
	
	@FindBy(id="group_724865818_2")
	private WebElement femaleElement;
	
	@FindBy(id="group_724865818_1")
	private WebElement maleElement;
	
	@FindBy(id="ss-submit")
	private WebElement submitButton;
	
	@FindBy(css="div[class='ss-custom-resp']")
	private WebElement successMessageElement;
	
	@FindBy(css="div[class='required-message']")
	private List<WebElement> requiredMessageElement;
	
	@FindBy(css="select[id='entry.437979468_month']")
	private WebElement monthElement;
	
	@FindBy(css="div[class*='ss-datetime-box goog-inline-block']")
	private WebElement dropdownDateElement;
	
	private WebDriver driver;
	
	private boolean toCheck;
	
	String[] formatStrings = {"dd/mm/yyyy", "dd-mm-yyyy", "mm/dd/yyyy"};
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
	
	Actions builder;

	Date date = null;
	
	public FormPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		builder = new Actions(driver);
		//this.driver = getDriver();
	}
	
	
//	public void put_in_hashMap(){
//		HashMap<String, WebElement> map = new HashMap<String, WebElement>();
//		map.put("firstName", firstNameElement);
//	}
	
	public void verifyField(String field, String val) throws ParseException, InterruptedException{
		System.out.println("entered key is:" +field);
		System.out.println("entered value is:" +val);
		HashMap<String, WebElement> map = new HashMap<String, WebElement>();
		map.put("firstName", firstNameElement);
		map.put("lastName", lastNameElement);
		map.put("phoneNumber", phoneNumberElement);
		map.put("email", emailElement);
		map.put("date", dateElement);
		if(map.containsKey(field)){
			WebElement value = map.get(field);
			value.sendKeys(val);
			formElement.click();
			
			
		}
			 	
	}
	
	public static String theMonth(int month){
	    String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	    return monthNames[month-1];
	}
	
	public Date parseDate(String[] formatStrings, String datetoParse){
		for (String formatString : formatStrings)
	    {
	        try
	        {
	            return new SimpleDateFormat(formatString).parse(datetoParse);
	        }
	        catch (ParseException e) {}
	    }
		
		return null;
	}
	
	
	
	public void check_for_all_fields(String suffix, String firstName, String lastName, String phoneNumber, String email, String datetoParse, String gender) throws InterruptedException, ParseException{
		Date date = parseDate(formatStrings, datetoParse);
		
		//Date date = formatter.parse(datetoParse);
		
	
		System.out.println(suffix);
		
		Select sel= new Select(suffixElement);
		sel.selectByValue(suffix);
		firstNameElement.sendKeys(firstName);
		lastNameElement.sendKeys(lastName);
		phoneNumberElement.sendKeys(phoneNumber);
		emailElement.sendKeys(email);
		if(System.getProperty("browser").equalsIgnoreCase("firefox")){
			if(!StringUtils.isEmpty(datetoParse)){
			String[] dateParts = datetoParse.split("/");
			String date1 = dateParts[0];
			String month = dateParts[1];
			String year = dateParts[2];
			String monthToChoose = theMonth(Integer.parseInt(month));
			WebElement dropdownList = dropdownDateElement.findElement(By.id("entry.437979468_month"));
			Select monthToSelect = new Select(dropdownList);
			List<WebElement> monthValues = monthToSelect.getOptions();
			for(int i=0; i<monthValues.size(); i++){
				if(monthValues.get(i).getText().equals(monthToChoose)){
					monthValues.get(i).click();
				}
			}
			
			WebElement dateValueList = dropdownDateElement.findElement(By.id("entry.437979468_day"));
			Select dateToSelect = new Select(dateValueList);
			List<WebElement> dateValues = dateToSelect.getOptions();
			for(int j=0; j<dateValues.size(); j++){
				if(dateValues.get(j).getText().equals(date1)){
					dateValues.get(j).click();
				}
			}
			
			WebElement yearValueList = dropdownDateElement.findElement(By.id("entry.437979468_year"));
			Select yearToSelect = new Select(yearValueList);
			List<WebElement> yearValues = yearToSelect.getOptions();
			for(int j=0; j<yearValues.size(); j++){
				if(yearValues.get(j).getText().equals(year)){
					yearValues.get(j).click();
				}
			}
			
			
		}
			
		}else
		{
			dateElement.sendKeys(formatter.format(date));
			System.out.println("date is:" +formatter.format(date));
			System.out.println("fetched date is:" +dateElement.getText());
		}
		
		if(gender.equals("Female")){
			femaleElement.click();
		}
		else if(gender.equals("Male")){
			maleElement.click();
		}
		
		submitButton.click();
	}
	
	
	
	public void check_error_message(String error) throws InterruptedException{
		
		System.out.println("is element ther:" +toCheck);
		String actualError = findErrorElement.getAttribute("title");
		System.out.println("error expected:" +error);
		System.out.println("error is" +actualError);
		if(!actualError.equals(error)){
			throw new AssertionError("The pattern is invalid for the field. Please pass a valid pattern");
		}
	}
	
	
	
	/*
	public void check_error_message(String error) throws InterruptedException {

		for (WebElement element : errorMessageElement) {
			System.out.println(element.getText());
			System.out.println("expected is" +error);
			if (element.isDisplayed())
				//System.out.println("actual is" +element.getText());
				//System.out.println("expected is" +error);
				assertThat("Error not displayed", element.getText(), is(error));
		}
	}
	
	*/
	
	/*
	public void check_error_message(String error){
		String actual = emailElement.findElement(By.cssSelector("title")).getText();
		System.out.println("actual error is:" +actual);
		System.out.println("expeced error is:" +error);
		if(!error.equals(actual))
			throw new AssertionError("error message is not shown");
		
		
	}
	*/
	
	/*
	public void check_required_message(String error) {
		System.out.println("error message is" +error);
		for (WebElement element : requiredMessageElement) {
			System.out.println("the message needed is:"+element.getText());
			//if(element.isDisplayed())
				if(!element.getText().equals(error))
					throw new AssertionError("Required message is not displayed");
				
				//assertThat("Required message not displayed",element.getText(), is(error));
			
		}
		
	}
	*/
	
	
	public void check_required_message(String error) {
		for(WebElement ele : requiredMessageElement){
			
			if(ele.isDisplayed()){
				System.out.println("the error is:" +ele.getText());
				System.out.println("the expected error is:" +error);
				if(!ele.getText().equals(error)){
					throw new AssertionError("required message to be displayed");
			
				}
			}
			//else{
				//throw new AssertionError("damn it!");
			//}
	}
	}
	
	
	
	public void form_submitted_successfully(String successMessage){
		String expected = successMessageElement.getText();
		System.out.println("expected message:" +expected);
		if(!expected.equals(successMessage))
			throw new AssertionError("the form is not submitted successfully");
		
	}
	
	
	
	public void verify_for_empty(String missingField,String suffix,String firstName,String lastName,String phoneNumber,String email,String datetoParse,String gender) throws ParseException{
		System.out.println("date is:" +datetoParse);
		Select sel= new Select(suffixElement);
		sel.selectByValue(suffix);
		firstNameElement.sendKeys(firstName);
		lastNameElement.sendKeys(lastName);
		phoneNumberElement.sendKeys(phoneNumber);
		emailElement.sendKeys(email);
		
		if(System.getProperty("browser").equalsIgnoreCase("firefox")){
			if(!StringUtils.isEmpty(datetoParse)){
			String[] dateParts = datetoParse.split("/");
			String date = dateParts[0];
			String month = dateParts[1];
			String year = dateParts[2];
			String monthToChoose = theMonth(Integer.parseInt(month));
			WebElement dropdownList = dropdownDateElement.findElement(By.id("entry.437979468_month"));
			Select monthToSelect = new Select(dropdownList);
			List<WebElement> monthValues = monthToSelect.getOptions();
			for(int i=0; i<monthValues.size(); i++){
				if(monthValues.get(i).getText().equals(monthToChoose)){
					monthValues.get(i).click();
				}
			}
			
			WebElement dateValueList = dropdownDateElement.findElement(By.id("entry.437979468_day"));
			Select dateToSelect = new Select(dateValueList);
			List<WebElement> dateValues = dateToSelect.getOptions();
			for(int j=0; j<dateValues.size(); j++){
				if(dateValues.get(j).getText().equals(date)){
					dateValues.get(j).click();
				}
			}
			
			WebElement yearValueList = dropdownDateElement.findElement(By.id("entry.437979468_year"));
			Select yearToSelect = new Select(yearValueList);
			List<WebElement> yearValues = yearToSelect.getOptions();
			for(int j=0; j<yearValues.size(); j++){
				if(yearValues.get(j).getText().equals(year)){
					yearValues.get(j).click();
				}
			}
			
			
		}
		}
		else{
			if(!StringUtils.isEmpty(datetoParse)){
				date = formatter.parse(datetoParse);
				System.out.println("date is:" +formatter.format(date));
				dateElement.sendKeys(formatter.format(date));
			}
		}
		if(gender.equals("Female")){
			femaleElement.click();
		}
		else if(gender.equals("Male")){
			maleElement.click();
		}
		submitButton.submit();
		
	}
	
	
	public void form_should_not_be_submitted(String responseUrl){
		//System.out.println("url is:" +driver.getCurrentUrl());
		//if(successMessageElement.isDisplayed())
			//throw new AssertionError("the form should not be submitted successfully");
		if(!responseUrl.contains("viewform"))
			throw new AssertionError("the form should not be submitted successfully and should throw an error for the two unrelated fields");
		}
	


}
