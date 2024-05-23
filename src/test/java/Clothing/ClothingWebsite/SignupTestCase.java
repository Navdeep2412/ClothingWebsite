package Clothing.ClothingWebsite;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SignupTestCase extends Base {

@Test(dataProvider="getSignupData")
public void userRegistration(HashMap<String,String>input) throws IOException
{
	Login_Page loginPage=launchApplication();
	Registration register=new Registration(driver);
	register.signup(input.get("namesignup"),input.get("emailsignup"),input.get("pwd"),
			input.get("days"),input.get("months"),input.get("years"),
			input.get("fname"), input.get("lname"),
			input.get("address"), input.get("country"), input.get("state"),
			input.get("city"),input.get("zipcode"), input.get("mobile"));
	String message=register.accountCreationVerify();
	Assert.assertEquals(message, "ACCOUNT CREATED!");
	ProductCatalogue productCatalogue=register.goToProductPage();
	
}


@DataProvider
public Object[][] getSignupData() throws IOException
{
	
	List<HashMap<String,String>> data=getJsondataToMap("E:\\eclipse-workspace\\ClothingWebsite\\src\\test\\java\\Resources\\registrationDetails.json");
	
	return new Object[][]{{data.get(0)}};
}

}
