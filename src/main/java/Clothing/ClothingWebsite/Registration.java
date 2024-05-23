package Clothing.ClothingWebsite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import AbstractComponents.AbstractComponents;

public class Registration extends AbstractComponents {

	public WebDriver driver;
	Select dropdown;
	public Registration(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@data-qa='signup-name']")
	WebElement nameSignup;
	@FindBy(xpath="//input[@data-qa='signup-email']")
	WebElement emailSignup;
	@FindBy(xpath="//button[@data-qa='signup-button']")
	WebElement btnSignup;
	@FindBy(xpath="//div[@class='login-form']/h2/b")
	WebElement accountinfo;
	@FindBy(xpath="//input[@type='password']")
	WebElement passwordSignup;
	@FindBy(id="days")
	WebElement day;
	@FindBy(id="months")
	WebElement month;
	@FindBy(id="years")
	WebElement year;
	@FindBy(xpath="//input[@data-qa='first_name']")
	WebElement firstName;
	@FindBy(xpath="//input[@data-qa='last_name']")
	WebElement lastName;
	@FindBy(xpath="//input[@data-qa='address']")
	WebElement address;
	@FindBy(id="country")
	WebElement countrySignup;
	@FindBy(xpath="//input[@data-qa='state']")
	WebElement stateSignup;
	@FindBy(xpath="//input[@data-qa='city']")
	WebElement citySignup;
	@FindBy(xpath="//input[@data-qa='zipcode']")
	WebElement zipCode;
	@FindBy(xpath="//input[@data-qa='mobile_number']")
	WebElement mobileNum;
	@FindBy(xpath="//button[@data-qa='create-account']")
	WebElement createAccountBtn;
	@FindBy(xpath="//h2[@class='title text-center']")
	WebElement accountText;

	
	public void signup(String nsignup,String esignup,String pwd,String days, String months, 
			String years,String fname, String lname, String addr, String country, 
			String state, String city
			,String zipcode, String mobile)
	{
	   nameSignup.sendKeys(nsignup);
	   emailSignup.sendKeys(esignup);
	   btnSignup.click();
	   
	   waitForElementToAppear(accountinfo);
	 javaScriptExe(driver);
	   passwordSignup.sendKeys(pwd);
	   
	   staticdropdown(day,days);
	  // dropdown.selectByVisibleText(days);
	   staticdropdown(month,months);
	  // dropdown.selectByVisibleText(months);
	   staticdropdown(year,years);
	  // dropdown.selectByVisibleText(years);
	  
	   javaScriptExe(driver);
	   firstName.sendKeys(fname);
	   lastName.sendKeys(lname);
	   address.sendKeys(addr);
	   javaScriptExe(driver);
	   staticdropdown(countrySignup,country);
	  // dropdown.selectByVisibleText(country);
	   stateSignup.sendKeys(state);
	   citySignup.sendKeys(city);
	   zipCode.sendKeys(zipcode);
	   javaScriptExe(driver);
	   mobileNum.sendKeys(mobile);
	   
	  
	   createAccountBtn.click();
	   
	  
	   
	   }
	public String accountCreationVerify()
	{
		 waitForElementToAppear(accountText);
		   String messageForAccount=accountText.getText();
		   return  messageForAccount;
	}
	
	
	

}
