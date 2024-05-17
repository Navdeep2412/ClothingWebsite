package Clothing.ClothingWebsite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;



public class CheckoutPage extends AbstractComponents {

	public  WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(css="a.check_out")
	WebElement proceedToPayment;
	
	public PaymentPage goToPayment()
	{
		javaScriptExe1(driver);
		
		proceedToPayment.click();
		return new PaymentPage(driver);
	}
	
	
	

}
