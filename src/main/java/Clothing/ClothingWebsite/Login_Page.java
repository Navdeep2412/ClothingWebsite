package Clothing.ClothingWebsite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class Login_Page extends AbstractComponents {
	public WebDriver driver;
	
	public Login_Page(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	@FindBy(xpath="//input[@data-qa='login-email']")
	WebElement Wemail;
	@FindBy(xpath="//input[@data-qa='login-password']")
	WebElement Wpassword;
	@FindBy(xpath="//button[@data-qa='login-button']")
	WebElement loginbtn;
	@FindBy(xpath="//form[@action='/login']/p")
	WebElement errorMsg;
	
	public void goTo()
	{
		driver.get("https://www.automationexercise.com/login");
	}
	public ProductCatalogue loginApp(String email,String password)
	{
		Wemail.sendKeys(email);
		Wpassword.sendKeys(password);
		loginbtn.click();
		return new ProductCatalogue(driver);
	}
	 public String getErrorMessage()
	  {
		 waitForElementToAppear(errorMsg);
		return  errorMsg.getText();
	  }

}
