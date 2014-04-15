package automation.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class FormResponsePage extends RootClass {
	
	public FormResponsePage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}

}
