package Clothing.ClothingWebsite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	public WebDriver driver;
	Login_Page loginPage;
	static ExtentReports extent;
	public WebDriver initiateDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"E:\\eclipse-workspace\\ClothingWebsite\\src\\test\\java\\Resources\\GlobalProperties.properties");
		prop.load(fis);
		String browsername=System.getProperty("browser")!=null ? 
				System.getProperty("browser"):prop.getProperty("browser"); 
		if (browsername.contains("chrome")) 
		{
			ChromeOptions options=new ChromeOptions(); //for jenkins headless
			
			WebDriverManager.chromedriver().setup();
			if(browsername.contains("headless"))
			{
				options.addArguments("headless");
		} 
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if (browsername.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		}
		
		else if (browsername.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
		
	}

	@BeforeMethod
	public Login_Page launchApplication() throws IOException {

		WebDriver driver = initiateDriver();
		loginPage = new Login_Page(driver);
		loginPage.goTo();
		return loginPage;
	}
	//data provider method
	public List<HashMap<String, String>> getJsondataToMap(String filepath) throws IOException 
	{
		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}
	
	//screenshot method
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source,
				new File("E:\\eclipse-workspace\\ClothingWebsite\\src\\test\\java\\Resources\\reports" + testCaseName
						+ ".png"));
		return "E:\\eclipse-workspace\\ClothingWebsite\\src\\test\\java\\Resources\\reports" + testCaseName
				+ ".png";
	}
	
	//for extent reports
	
	public static ExtentReports getReportObject()
	{
		String path="E:\\eclipse-workspace\\ClothingWebsite\\src\\test\\java\\Resources\\index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setReportName("Clothing Website Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Navdeep");
		return extent;
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void teardown() {
		driver.quit();
	}
	
	}


