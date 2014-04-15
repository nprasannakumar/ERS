package automation.ui;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;




public class SquareRootCalculatorPage extends RootClass{
	/*
	@FindBy(name="num")
	private WebElement numberElement;
	
	@FindBy(name="button")
	private WebElement buttonElement;
	
	@FindBy(name="answer")
	private WebElement answerElement;
	*/
	
	String expected = null;
	
	@FindBy(id="result")
	private WebElement numberElement;
	
	@FindBy(name="input")
	private WebElement inputElement;
	
	@FindBy(css="input[class='operation']")
	//private WebElement operationElement;
	private List<WebElement> operationElement;

	public SquareRootCalculatorPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void find_square_root(String number) throws InterruptedException{
		numberElement.sendKeys(number);
		//buttonElement.click();
		for(WebElement element : operationElement){
			String val = element.getAttribute("value");
			if(val.equals("sqrt"))
				element.click();
		}
		
		
	}
	
	public void check_error_message(String errorMessage){
		String actualErrorMessage = numberElement.getAttribute("value");
		System.out.println("expected:" +errorMessage);
		System.out.println("error message is:" +actualErrorMessage);
		if(!actualErrorMessage.equals(errorMessage))
			throw new AssertionError("The error message is not as expected");
		
	}
	
	public void check_sqrt_of_number(double num){
		String actual = numberElement.getAttribute("value");
		if(!actual.matches("^-?\\d*\\.\\d+$")){
			 Integer i = (int) num;
			 expected = String.valueOf(i);
			 System.out.println("expected is:" +expected);
		}
		else{
			 expected = String.valueOf(num);
		}
			
		System.out.println("actual is:" +actual);
		
		if(!actual.equals(expected))
			throw new AssertionError("The square root is not calculated accurately");
	}
	

}
