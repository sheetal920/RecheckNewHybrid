package com.HybridFrameworkStructure.TestCases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.HybridFrameworkStructure.pageObjects.LoginPage;
import com.HybridFrameworkStructure.TestCases.BaseClass;

import junit.framework.Assert;

public class TC_LoginTest_001 extends BaseClass
{
	//yaha only single method is required
    @Test
	public void loginTest() throws IOException
	{
    	//setUp(); isko call krni ki jrurat nhi hai base class extends kia hai , extend class me jo beforeClass ya after method hai wo khud run ho jayega 
    	loger.info("URL will enter"); //loger variable ko globally declare kra, otherwise error aa rha tha
    	driver.get(URL);
    	loger.info("URL entered");
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		lp.setPassword(password);
		lp.submitClick();
		loger.info("Submit button clicked");
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			captureScreenshot(driver,"loginTest");
			Assert.assertTrue(false);
		}
	}
}
