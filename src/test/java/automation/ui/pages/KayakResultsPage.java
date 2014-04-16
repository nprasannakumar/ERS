package automation.ui.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KayakResultsPage extends RootClass{
	
	private WebDriver driver;
	
	@FindBy(css="div[class*='pricerange']")
	private WebElement priceListElement;
	
	@FindBy(css="a[class*='results_price bookitprice']")
	private WebElement priceElement;
	
	@FindBy(css="select[id='sortselectlist']")
	private WebElement selectElement;
	
	public KayakResultsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void verify_results() throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("inlineAdBookPrice")));
		ArrayList<String> priceList = new ArrayList<String>();
		WebElement actualLowestPrice = driver.findElement(By.cssSelector("a.results_price.bookitprice"));
		System.out.println("the lowest price is:"+actualLowestPrice.getText());
		
		
		
		List<WebElement>listOfPage = driver.findElements(By.cssSelector("div.flightresult.resultrow"));
		for(WebElement page: listOfPage){
		
		WebElement elementBeforePrice = page.findElement(By.cssSelector("div.pricerange"));
		System.out.println("the price is:" +elementBeforePrice.getText());
		List<WebElement> price = elementBeforePrice.findElements(By.tagName("a"));
		
		for(WebElement ele: price){
			String prices = ele.getText();
			System.out.println("price is:" +ele.getText());
			priceList.add(prices);
		 }
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Collections.sort(priceList);
		}
		Thread.sleep(3000);
		
		if(!actualLowestPrice.getText().equals(priceList.get(0))){
			throw new AssertionError("the prices are not equal");
		}
		
	}
	
	
	

}
