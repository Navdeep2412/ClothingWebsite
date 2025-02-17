package Clothing.ClothingWebsite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {

	public WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css=".col-sm-9 p")
	WebElement ConfirmMessage;
	@FindBy(css=".check_out")
	WebElement downloadinvoice;
	
	public String verifyConfirmMessage()
	{
		return ConfirmMessage.getText();
	}
	 public void downloadInvoice()
	 {
		 downloadinvoice.click();
	 }
	
	
	

}
