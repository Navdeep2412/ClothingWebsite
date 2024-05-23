package Clothing.ClothingWebsite;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.temporal.TemporalAmount;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelectMultipleProductsTestCase extends Base {
	@Test(dataProvider="getData")
	public void submitMultipleProductOrder(HashMap<String,String>ins) throws InterruptedException, IOException {
		String[] productName = {"Men Tshirt","Winter Top"};
	
		Login_Page loginPage=launchApplication();
		ProductCatalogue productCatalogue=loginPage.loginApp(ins.get("email"),ins.get("password"));
		for(String prods: productName)
		{
			productCatalogue.addmultipleProduct(prods);
		}
		CartPage1 cartpage=productCatalogue.goToCartViaHeader();
		for(String prods: productName)
		{
			Boolean match = cartpage.verifyProduct(prods);
			Assert.assertTrue(match);
		}
		
		CheckoutPage checkoutPage=cartpage.goToCheckout();	

		PaymentPage paymentPage=checkoutPage.goToPayment();
		paymentPage.paymentDetails(ins.get("cardholder"),ins.get("cardnum"),ins.get("cvcnum"),ins.get("month"),ins.get("year"));
		ConfirmationPage confirmationPage=paymentPage.goToConfirmation();
		
		String message=confirmationPage.verifyConfirmMessage();
		Assert.assertEquals(message,"Congratulations! Your order has been confirmed!");

	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		String jsonContent = FileUtils.readFileToString(new File("E:\\eclipse-workspace\\ClothingWebsite\\src\\test\\java\\Resources\\lg.json"), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return new Object[][]{{data.get(0)}};
	}
}
   