package Clothing.ClothingWebsite;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RemoveProductsFromCartTestCase extends Base {
	@Test(dataProvider="getData")
	public void removeProducts(HashMap<String,String> input) throws IOException  {
		
		Login_Page loginPage=launchApplication();
		ProductCatalogue productCatalogue=loginPage.loginApp(input.get("email"),input.get("password"));
		
		productCatalogue.addProductToCart(input.get("productName"));
		
		CartPage1 cartpage=productCatalogue.goToCartPage();
		Boolean verifyRemovedProduct = cartpage.removeProduct(input.get("productName"));
		Assert.assertTrue(verifyRemovedProduct);
		String message=cartpage.cartEmptyMessage();
		Assert.assertEquals(message,"Cart is empty!");
		
		}
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List<HashMap<String,String>> data=getJsondataToMap("E:\\eclipse-workspace\\ClothingWebsite\\src\\test\\java\\Resources\\loginDetails.json");
		
		return new Object[][]{{data.get(0)}};
	}

}
