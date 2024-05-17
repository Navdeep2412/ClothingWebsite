package Clothing.ClothingWebsite;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;



public class ErrorValidationsForSite extends Base {
	
	@Test
	public void Loginwrong() throws IOException
	{
		
		Login_Page loginPage=launchApplication();
		ProductCatalogue productCatalogue=loginPage.loginApp("ngill@email.com","Patiala@12");
		Assert.assertEquals("Incorrect email or password",loginPage.getErrorMessage());
		
	}
	

}
