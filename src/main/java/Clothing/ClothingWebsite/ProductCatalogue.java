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
	
	//@FindBy(xpath="//div[@class='overlay-content']//a[@class='btn btn-default add-to-cart']")
	//WebElement addToCart;
	//By addToCart=By.xpath("//div[@class='overlay-content']//a[@class='btn btn-default add-to-cart']");
	By addToCart=By.className("btn");
	@FindBy(xpath="//div[@class='productinfo text-center']")
	WebElement overlay;
	@FindBy(css="a[href='/products']")
	WebElement productLink;
	@FindBy(css="#search_product")
	WebElement searchProduct;
	@FindBy(xpath="//button[@id='submit_search']")
	WebElement submitSearchBtn;
	@FindBy(xpath="//div[@class='features_items']")
	WebElement searchedItems;
	@FindBy(xpath="//div[@class='features_items']//div[@class='productinfo text-center']//p")
	WebElement prodname;
	@FindBy(css="button[data-dismiss='modal']")
	WebElement continuShopping;
	
	public WebElement selectProduct(String productName)
	{
		javaScriptExe(driver);
		WebElement product = products.stream()
				.filter(prod -> prod.findElement(By.cssSelector("p")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);

		return product;
		
	}
	
	public String searchProduct(String productName)
	{
		waitForElementToAppear(productLink);
		productLink.click();		
		javaScriptExe(driver);
		waitForElementToAppear(searchProduct);
		searchProduct.click();
		searchProduct.sendKeys(productName);
		submitSearchBtn.click();
		javaScriptExe(driver);
		waitForElementToAppear(searchedItems);
		String match=prodname.getText();
		return match;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement product=selectProduct(productName);
		javaScriptExe(driver);
		waitForElementToAppear(overlay);
		//addToCart.click();
		product.findElement(addToCart).click();
	}
	
	public void addmultipleProduct(String productName)
	{
		WebElement mulProduct=selectProduct(productName);
		javaScriptExe(driver);
		waitForElementToAppear(overlay);
		mulProduct.findElement(addToCart).click();
		continuShopping.click();
		}
	
	
	
	


}
