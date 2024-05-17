package Clothing.ClothingWebsite;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractComponents.AbstractComponents;



public class CartPage1 extends AbstractComponents {
 public WebDriver driver;
	public CartPage1(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//table/tbody/tr/td[2]//following-sibling::h4")
	List<WebElement>cartproducts;
	@FindBy(css="a.btn")
	WebElement goToCheckoutbtn;
	
	public Boolean verifyProduct(String productName)
	{
		Boolean match=cartproducts.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		goToCheckoutbtn.click();
		return new CheckoutPage(driver);
	}
	
	
	

}
