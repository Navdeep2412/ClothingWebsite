package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Clothing.ClothingWebsite.CartPage1;

public class AbstractComponents {
	WebDriver driver;
	
public AbstractComponents(WebDriver driver) {
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	}

@FindBy(tagName="u")
WebElement goToCart;
	public void javaScriptExe(WebDriver driver)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,500)");
	}
	public void javaScriptExe1(WebDriver driver)
	{
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scroll(0,1500)");
	}
	
	
	public void waitForElementToAppear(WebElement el)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(el));
	}
	
	public CartPage1 goToCartPage()
	{
		goToCart.click();
		return new CartPage1(driver);
		
	}
	
}
