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
	@FindBy(xpath="//table/tbody/tr/td[6]/child::a/child::i[@class='fa fa-times']")//i[@class='fa fa-times']
	WebElement removeProduct;
	@FindBy(xpath="//span [@id='empty_cart']//p[@class='text-center'] /b")
	WebElement cartempty;
	
	public Boolean verifyProduct(String productName)
	{
		Boolean match=cartproducts.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(productName));
		return match;
	}
	public Boolean removeProduct(String productName)
	{
		Boolean match=cartproducts.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(productName));

		if(match==true)
		{
			removeProduct.click();
		}
		Boolean matchnotFound=cartproducts.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(productName));
		return matchnotFound;
	}
	
	public String cartEmptyMessage()
	{
		waitForElementToAppear(cartempty);
		String message=cartempty.getText();
		return message;
	}
	public CheckoutPage goToCheckout()
	{
		goToCheckoutbtn.click();
		return new CheckoutPage(driver);
	}
	
	
	

}
