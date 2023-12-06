package com.HybridFrameworkStructure.TestCases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.HybridFrameworkStructure.pageObjects.AddCustomerPage;
import com.HybridFrameworkStructure.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass 
{
	@Test //login TestCase class ke bad or jitne TC add krenge un sb par @test use krna hoga tbhi wo base class se connect kar payenge other wise TC run nhi honge console me kuch b run nhi hoga na hi browser invoke hoga
	public void AddnewCustomer() throws InterruptedException, IOException
	{
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		loger.info("User Name is provided");
		lp.setPassword(password);
		loger.info("Password is provided");
		lp.submitClick();
		loger.info("Submit button clicked");
		Thread.sleep(3000);
		AddCustomerPage ac=new AddCustomerPage(driver);
		loger.info("Click on Add Customer");
		ac.clickAddCustomer();
		loger.info("Enter customer Details");
		ac.custName("sheetal");
		ac.gend();
		Thread.sleep(2000);
		ac.Dob("11","05","1996");//ye firefox me issue kar rha but chrome me chal rha hai
		Thread.sleep(3000);
		ac.Address("India");
		ac.City("GR");
		ac.State("UP");
		ac.PIN(123456);
		ac.Mobile("1234335558");
		
		//random email id chahiye har bar
		String emailID=randomEmail()+"@gmail.com";
		ac.custemail(emailID);
		ac.Password("abcde");
		ac.submit();
		
		Thread.sleep(3000);
		loger.info("Validation Started");
		boolean value=driver.getPageSource().contains("Customer Registered Successfully!!!");
		if(value==true)
		{
			Assert.assertTrue(true);
			loger.info("testCase passed");
		}
		else
		{
			loger.info("testCase failed");
			captureScreenshot(driver,"AddnewCustomer"); //fail hone pr screenshot le rhe hai
		Assert.assertFalse(false);	
		}
		
	}
	
	/*
	public String randomEmail() //ye base class me rkhna chahiye wrna ye bas isi class tk limited rkhega
	{
		String generatedSrting=RandomStringUtils.randomAlphabetic(5); // 5 is length
		return generatedSrting;
		
	}
	
	public String randomnumber() //ye base class me rkhna chahiye wrna ye bas isi class tk limited rkhega
	{
		String generatedSrting1=RandomStringUtils.randomNumeric(5);
		return generatedSrting1;
		
	}
	*/
	
}
