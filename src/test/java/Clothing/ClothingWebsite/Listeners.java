package Clothing.ClothingWebsite;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;



public class Listeners extends Base implements ITestListener {
	
	 ExtentTest test;
	 ExtentReports extent=getReportObject();

	 @Override
	 public  void onTestStart(ITestResult result) {
	 test=extent.createTest(result.getMethod().getMethodName());
	 }

     @ Override
    public  void onTestSuccess(ITestResult result) {
     test.log(Status.PASS, "Test Passed");
      }
   @Override
   public  void onTestFailure(ITestResult result) {
	 
	  
   test.fail(result.getThrowable());
   try {
		driver=(WebDriver)result.getTestClass().getRealClass().getField("driver")
				.get(result.getInstance());
	//to get the driver
   } 
   catch (Exception e) 
   {
		
		e.printStackTrace();
	}
   //screenshot, attach to the report
 String filepath=null;
  
	try {
		filepath = getScreenshot(result.getMethod().getMethodName(),driver);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  
    test.addScreenCaptureFromPath(filepath,result.getMethod().getMethodName());
}
  
@Override
 public  void onFinish(ITestContext context) {
extent.flush();
 }
}


