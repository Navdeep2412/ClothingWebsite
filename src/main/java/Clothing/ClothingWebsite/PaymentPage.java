package Clothing.ClothingWebsite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class PaymentPage extends AbstractComponents {
 public WebDriver driver;
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//input[@name='name_on_card']")
	WebElement nameOnCard;
	@FindBy(xpath="//input[@name='card_number']")
	WebElement CardNumber;
	@FindBy(xpath="//input[@name='cvc']")
	WebElement cvc;
	@FindBy(xpath="//input[@name='expiry_month']")
	WebElement expiryMonth;
	@FindBy(xpath="//input[@name='expiry_year']")
	WebElement expiryYear;
	@FindBy(xpath="//button[@id='submit']")
	WebElement submitOrder;
	
	
	public void paymentDetails(String cardholder,String cardnum, String cvcnum,String month,String year)
	{
		nameOnCard.sendKeys(cardholder);
		CardNumber.sendKeys(cardnum);
		cvc.sendKeys(cvcnum);
		expiryMonth.sendKeys(month);
		expiryYear.sendKeys(year);
	}
	
	public ConfirmationPage goToConfirmation()
	{
		submitOrder.click();
		return new ConfirmationPage(driver);
	}
	

}
