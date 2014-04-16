package automation.ui.pages;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RootClass {

	private WebDriver driver;
	private WebDriverWait wait;
	private FileWriter fileWriter;
	private PrintWriter writer;
	private Date date;
	private SimpleDateFormat simpleDateFormat;

	public RootClass(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 5);
		pageWait();
		date = new Date();
		simpleDateFormat = new SimpleDateFormat("MM-DD-YY");
	}
	
	

	public WebDriver getDriver() {
		return this.driver;
	}

	public TreeMap<String, Integer> createMap(TreeMap<String, Integer> map,
			String key) {
		if (map.containsKey(key))
			map.put(key, map.get(key) + 1);
		else
			map.put(key, 1);
		return map;
	}

	public WebElement findByCss(String cssPath, WebElement element) {
		return element.findElement(By.cssSelector(cssPath));
	}

	public WebElement findByCss(String cssPath) {
		return driver.findElement(By.cssSelector(cssPath));
	}

	public boolean isElementPresent(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}

	public boolean isElementPresent(String cssPath, WebElement element) {
		try {
			findByCss(cssPath, element);
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}

	public boolean isElementPresent(String cssPath) {
		try {
			findByCss(cssPath);
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}

	public void writeToFile(String content) throws IOException {
		fileWriter = new FileWriter(System.getProperty("user.home")
				+ "/Desktop/" + "BIR created on "
				+ simpleDateFormat.format(date) + ".txt", true);
		writer = new PrintWriter(fileWriter);
		writer.println(content);
		writer.close();
	}

	public String getIssueName(String fullName) {
		String[] issueName = fullName.split("-");
		return issueName[0];
	}

	public void clearAndType(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}

	private JavascriptExecutor js;

	public boolean pageWait() {
		js = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jqueryWait = new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver arg0) {
				try {
					if ((Integer) js.executeScript("return jquery.active") == 0)
						return true;
					else
						return false;
				} catch (WebDriverException e) {
					System.out.println("No Jquery is present");
					return true;
				}

			}

		};

		ExpectedCondition<Boolean> javascriptWait = new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver arg0) {
				if (js.executeScript("return document.readyState").toString()
						.equals("complete"))
					return true;
				else
					return false;

			}
		};
		return wait.until(jqueryWait) && wait.until(javascriptWait);

	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public void acceptAlert() {
		if (isAlertPresent())
			driver.switchTo().alert().accept();
	}

	public void dismissAlert() {
		if (isAlertPresent())
			driver.switchTo().alert().dismiss();
	}

	public boolean isSubWindowPresent() {
		return (driver.getWindowHandles().size() > 0);
	}

	public void switchToSubWindow() {
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		String subWindow = null;
		while (iterator.hasNext()) {
			subWindow = iterator.next();
		}
		driver.switchTo().window(subWindow);
	}

	public WebElement getUniqueElement(List<WebElement> listOfWE, int index) {
		return listOfWE.get(index - 1);
	}
	
	public boolean isDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean waituntil_visibilityOfElementLocated(org.openqa.selenium.WebElement element) {
		try{
		return waituntil_visibilityOfElementLocated(element); /* compiled code */
		}
		catch(Exception e){
			return false;
		}
	}
}
