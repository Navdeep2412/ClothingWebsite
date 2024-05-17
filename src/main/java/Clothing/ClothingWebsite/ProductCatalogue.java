package Clothing.ClothingWebsite;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	public WebDriver driver;
	public ProductCatalogue(WebDriver driver)
	{ 
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	@FindBy(xpath="//div[@class='productinfo text-center']")
	List<WebElement> products;
	By addToCart=By.className("btn");
	@FindBy(xpath="//div[@class='productinfo text-center']")
	WebElement overlay;
	
	
	public WebElement selectProduct(String productName)
	{
		javaScriptExe(driver);
		WebElement product = products.stream()
				.filter(prod -> prod.findElement(By.cssSelector("p")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);

		return product;
		
	}
	
	public void addProductToCart(String productName)
	{
		WebElement product=selectProduct(productName);
		waitForElementToAppear(overlay);
		product.findElement(addToCart).click();
	}
	
	
	


}
