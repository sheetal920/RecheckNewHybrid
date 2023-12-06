package com.HybridFrameworkStructure.TestCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.HybridFrameworkStructure.Utilities.XLUtility;
import com.HybridFrameworkStructure.pageObjects.LoginPage;

import junit.framework.Assert;

public class TC_LoginExcelDDT_002 extends BaseClass {

	//2 method yaha chhaiye one will do login process and other for dataProvider
	
	@Test(dataProvider="LoginData")
	public void LoginDDT(String user,String password) throws InterruptedException
	{
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(user);
		loger.info("User name entered");
		lp.setPassword(password);
		loger.info("Password entered");
		lp.submitClick();
		Thread.sleep(3000);
		
		
		//invalid login par alert generate ho rha , usko OK krne k bad hi ham login Page par wapas aa payenge
	 if(isAlertPresent()==true)
	 {
		 driver.switchTo().alert().accept(); //close alert and focus again on login page
		 driver.switchTo().defaultContent();
		 loger.warn("Login failed");
		 Assert.assertTrue(false);
		 
	 }
	 else// ye valid case hai
	 {
		 Assert.assertTrue(true);
		 loger.warn("Login Passed");
		 Thread.sleep(3000);
		 lp.LogOutClick();
		 driver.switchTo().alert().accept(); //close Logout alert
		 driver.switchTo().defaultContent();
	 }
	}
	
	public boolean isAlertPresent()//this is user define method to check if alert is present or not
	{ 
		//agar alert present nhi hai to ye exception throw krega so hme try catch block me rkhna hoga
		try
		{
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}		
	}

	@DataProvider(name="LoginData") // DataProvider ka name yaha mention krna hi hoga
	String[][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/com/HybridFrameworkStructure/testDataFile/TestExcel.xlsx";  //yaha hme excel file jisme hmne user and passwrd rkhe hai wo loaction btana hai
	//Now we will call the methods from XLutility
		int rownumber=XLUtility.getRowCount(path, "Sheet1"); //file path and excel me sheet name btana hoga
		int colnumber=XLUtility.getCellCount(path, "Sheet1", 1); //0 ya 1; koi bhi row utha lo column to sbme same hi rhega

		
		String logindata[][]=new String[rownumber][colnumber];
		for(int i=1;i<=rownumber;i++)
		{
			for(int j=0;j<colnumber;j++)
			{
				logindata[i-1][j]=XLUtility.getCellData(path, "Sheet1", i, j); // yaha ham excel sheet ka 1,0 place ke data 0,0 par rakh rhe and so on
			}
		}
		return logindata;
	}
}
