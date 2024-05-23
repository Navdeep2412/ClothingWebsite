package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Clothing.ClothingWebsite.CartPage1;
import Clothing.ClothingWebsite.ProductCatalogue;

public class AbstractComponents {
	WebDriver driver;
	
public AbstractComponents(WebDriver driver) {
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	}

@FindBy(tagName="u")
WebElement goToCart;
@FindBy(css=".btn-primary")
WebElement goToProductPage;
@FindBy(css="#search_product")
WebElement searchproduct;
@FindBy(xpath="//div[@class='shop-menu pull-right']/child::ul/child::li[3]/child::a")
WebElement goToCartViaheader;
@FindBy(css="a[href='/logout']")
WebElement logOut;
@FindBy(css="a[href='/login']")
WebElement login;

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
	
	public void javaScriptExeUp(WebDriver driver)
	{
		JavascriptExecutor jsup = (JavascriptExecutor) driver;
		jsup.executeScript("window.scroll(0,0)");
	}
	
	public void waitForElementToAppear(WebElement el)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(el));
	}
	
	public void staticdropdown(WebElement element,String value)
	{
		Select dropdown = new Select(element);
        dropdown.selectByVisibleText(value);
		
	}
	
	public ProductCatalogue goToProductPage()
	{
		goToProductPage.click();
		waitForElementToAppear(searchproduct);
		return new ProductCatalogue(driver);
	}
	
	
	public CartPage1 goToCartPage()
	{
		goToCart.click();
		return new CartPage1(driver);
		
	}
	public CartPage1 goToCartViaHeader()
	{
		javaScriptExeUp(driver);
		goToCartViaheader.click();
		return new CartPage1(driver);
	}
	 public void logout()
	 {
		 logOut.click();
		 waitForElementToAppear(login);
	 }
}
