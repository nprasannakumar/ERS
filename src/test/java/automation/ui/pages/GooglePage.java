package automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GooglePage extends RootClass {
	
	@FindBy(id="entry_27481262")
	private WebElement userNameElement;
	
	@FindBy(id="entry_1565250029")
	private WebElement lastNameElement;
	
	@FindBy(id="ss-submit")
	private WebElement submitButtonElement;
	
	@FindBy(css="div[class='ss-custom-resp']")
	private WebElement successMessageElement;
	
	@FindBy(css="div[class='required-message']")
	private WebElement requiredMessageElement;
	
	@FindBy(css="div[class='error-message']")
	private WebElement errorMessageElement;
	
	
	private WebDriver driver;

	public GooglePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void fill_in_details(String uname) throws InterruptedException{
		//clearAndType(userNameElement, uname);
		System.out.println("Uname is:" +uname);
		userNameElement.sendKeys(uname);
		String check = userNameElement.getText();
		System.out.println("username element is:" +check);
		//submitButtonElement.click();
		lastNameElement.click();
	}
	
	public void fill_in_firstAndLast(String fname, String lname){
		userNameElement.sendKeys(fname);
		lastNameElement.sendKeys(lname);
		//submitButtonElement.click();
		
	}
	
	public void form_submitted_successfully(){
		//String expected = "Your response has been recorded.";
		//String actual = successMessageElement.getText();
		//if(!actual.equals(expected))
			//throw new AssertionError("error with username");
		//return new FormResponsePage(driver);
		if(errorMessageElement.isDisplayed())
			throw new AssertionError("error with first name");
	}
	
	public void form_throws_error_on_empty(){
		String expected = "This is a required question";
		//System.out.println("expected required text is:" +expected);
		String actual = requiredMessageElement.getText();
		//System.out.println("actual required text is:" +actual);
		if(!actual.equals(expected))
			throw new AssertionError("an error message is not seen even if the first name is empty");
		
	}
	
	public void form_throws_error_when_invalid(){
		String expected = "Must match pattern";
		//System.out.println("expected required text is:" +expected);
		String actual = errorMessageElement.getText();
		//System.out.println("actual required text is:" +actual);
		if(!actual.equals(expected))
			throw new AssertionError("an error message is not seen when the first name is invalid or too long");
	}

}
