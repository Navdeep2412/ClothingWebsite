package Clothing.ClothingWebsite;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class SearchProductByNameTestCase extends Base 
{
	@Test(dataProvider="getDataSearch")
public void submitOrderBySearchProduct(HashMap<String,String> input) throws IOException  {
		
		Login_Page loginPage=launchApplication();
		ProductCatalogue productCatalogue=loginPage.loginApp(input.get("email"),input.get("password"));
		String match=productCatalogue.searchProduct(input.get("productName"));
		Assert.assertTrue(match, true);
        productCatalogue.addProductToCart(input.get("productName"));
		
		CartPage1 cartpage=productCatalogue.goToCartPage();
		Boolean match1 = cartpage.verifyProduct(input.get("productName"));
		Assert.assertTrue(match1);
		CheckoutPage checkoutPage=cartpage.goToCheckout();	

		PaymentPage paymentPage=checkoutPage.goToPayment();
		paymentPage.paymentDetails(input.get("cardholder"),input.get("cardnum"),input.get("cvcnum"),input.get("month"),input.get("year"));
		ConfirmationPage confirmationPage=paymentPage.goToConfirmation();
		
		String message=confirmationPage.verifyConfirmMessage();
		Assert.assertEquals(message,"Congratulations! Your order has been confirmed!");
		
}
	@DataProvider
	public Object[][] getDataSearch() throws IOException
	{
		
		List<HashMap<String,String>> data=getJsondataToMap("E:\\eclipse-workspace\\ClothingWebsite\\src\\test\\java\\Resources\\loginDetails.json");
		
		return new Object[][]{{data.get(0)}};
	}
		
	

}
