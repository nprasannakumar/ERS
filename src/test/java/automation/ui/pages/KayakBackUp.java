
	
	package automation.ui.pages;

	import java.util.List;

  import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
  import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;

	public class KayakBackUp extends RootClass {
		
		private WebDriver driver;
		
		@FindBy(name="origin")
		private WebElement originElement;
		
		@FindBy(id="destination")
		private WebElement destinationElement;
		
		@FindBy(css="div[id='smartbox']")
		private WebElement dropDownElement;
		
		@FindBy(css="span[id='travel_dates-start-placeholder']")
		private WebElement startDateElement;
		
		@FindBy(css="r9-datepicker-item.r9-datepicker-enabled.r9-datepicker-item-hover.r9-datepicker-item-start>span")
		private WebElement datePickerElement;
		
		@FindBy(css="button[id='fdimgbutton']")
		private WebElement buttonElement;
		
		@FindBy(css="span[class='r9-datepicker-month-title']")
		private WebElement monthElement;
		
		@FindBy(id="cbPRICLINE_FDCMP2")
		private WebElement unselectElement;
		
		
		public KayakBackUp(WebDriver driver){
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
			
		}
		
		public void chooseCities(String src, String dest, String startDate, String endDate) throws InterruptedException{
			clearAndType(originElement, src);
			if(isElementPresent(dropDownElement)){
				if(dropDownElement.getText().contains(src)){
				dropDownElement.click();
				}
			}
			clearAndType(destinationElement, dest);
			if(isElementPresent(dropDownElement)){
				if(dropDownElement.getText().equals(dest)){
				dropDownElement.click();
				}
			}
			startDateElement.click();
			
			
			WebElement datePicker = driver.findElement(By.xpath("//*[@id='datepicker']/div[2]/span[2]/span[2]"));
			
			List<WebElement> rows = datePicker.findElements(By.cssSelector("span"));
				for(WebElement r: rows){
					List<WebElement> row = r.findElements(By.cssSelector("span"));
					for(WebElement re : row){
						List<WebElement> col = re.findElements(By.cssSelector("span"));
						for(WebElement co : col){
							if(co.getText().equals(startDate)){
								co.click();
							}
							if(co.getText().equals(endDate)){
								co.click();
							}
						}
					}
				}
			buttonElement.click();
		
			}
		

		}





