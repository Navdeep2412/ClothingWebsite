package Clothing.ClothingWebsite;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.Window;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;

public class SubmitOrderStandalone {

	@Test
	public void submitOrder() {
		String productName = "Men Tshirt";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.automationexercise.com/login");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("ngill@email.com");
		driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("Patiala@11");
		driver.findElement(By.cssSelector("button[data-qa='login-button']")).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,500)");
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='productinfo text-center']"));

		WebElement product = products.stream()
				.filter(prod -> prod.findElement(By.cssSelector("p")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);

		product.findElement(By.className("btn")).click();
		driver.findElement(By.tagName("u")).click();
		
		List<WebElement>cartproducts=driver.findElements(By.xpath("//table/tbody/tr/td[2]//following-sibling::h4"));
		Boolean match=cartproducts.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector("a.btn")).click();
		
		js.executeScript("window.scroll(0,1500)");
		driver.findElement(By.cssSelector("a.check_out")).click();
		
		driver.findElement(By.xpath("//input[@name='name_on_card']")).sendKeys("Nav");
		driver.findElement(By.xpath("//input[@name='card_number']")).sendKeys("322");
		driver.findElement(By.xpath("//input[@name='cvc']")).sendKeys("311");
		driver.findElement(By.xpath("//input[@name='expiry_month']")).sendKeys("12");
		driver.findElement(By.xpath("//input[@name='expiry_year']")).sendKeys("2029");
		driver.findElement(By.xpath("//button[@id='submit']")).click();
		
		String message=driver.findElement(By.cssSelector(".col-sm-9 p")).getText();
		Assert.assertEquals(message,"Congratulations! Your order has been confirmed!");
		driver.close();

	}

}
