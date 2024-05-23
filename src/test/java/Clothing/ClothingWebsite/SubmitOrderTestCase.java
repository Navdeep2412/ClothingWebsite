package Clothing.ClothingWebsite;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTestCase extends Base {
	@Test(dataProvider="getData")
	public void submitOrder(HashMap<String,String> input) throws IOException  {
		
		Login_Page loginPage=launchApplication();
		ProductCatalogue productCatalogue=loginPage.loginApp(input.get("email"),input.get("password"));
		
		productCatalogue.addProductToCart(input.get("productName"));
		
		CartPage1 cartpage=productCatalogue.goToCartPage();
		Boolean match = cartpage.verifyProduct(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartpage.goToCheckout();	

		PaymentPage paymentPage=checkoutPage.goToPayment();
		paymentPage.paymentDetails(input.get("cardholder"),input.get("cardnum"),input.get("cvcnum"),input.get("month"),input.get("year"));
		ConfirmationPage confirmationPage=paymentPage.goToConfirmation();
		
		String message=confirmationPage.verifyConfirmMessage();
		Assert.assertEquals(message,"Congratulations! Your order has been confirmed!");
		confirmationPage.downloadInvoice();
		confirmationPage.logout();
		
		
		}
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List<HashMap<String,String>> data=getJsondataToMap("E:\\eclipse-workspace\\ClothingWebsite\\src\\test\\java\\Resources\\loginDetails.json");
		
		return new Object[][]{{data.get(0)},{data.get(1)}};
	}


}
