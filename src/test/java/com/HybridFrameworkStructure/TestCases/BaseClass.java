package com.HybridFrameworkStructure.TestCases;



import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.HybridFrameworkStructure.Utilities.Readconfig;


public class BaseClass {
  //ye sb hmne base class me mention kiye common values but abhi to ye 3-4 aage or bhi add honge , so we will create seperate config.property file --> here we will use readConfig.java utility class
	/*
	public String URL="https://demo.guru99.com/v4";
	public String username="mngr515111";
	public String password="atedazY";
	*/
	
	Readconfig rc=new Readconfig();
	public String URL=rc.getApplicationURL();
	public String username=rc.getUser();
	public String password=rc.getPassword();
	
	
	public WebDriver driver;
	public Logger loger;
	
	@Parameters("browser")
	@BeforeClass
	public void setUp(String br) //br me parameter browser ki value rhegi
	{
		loger=Logger.getLogger("HybridFrameworkStructure"); // Log common hai sbke liye to iska setup base lass me kar denge and project/class name pass kar denge isme
		PropertyConfigurator.configure("log4j.propertiies");
		
		if(br.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", rc.getchromePath()); //System.getProperty("user.dir")+ "//Drivers2//chromedriver.exe":phle hmne ye use kia tha but Readconfig create krne k ba change kar dia ab it will give project home directory
			ChromeOptions cp=new ChromeOptions();
			cp.addArguments("--remote-allow-origins=*");
			driver=new ChromeDriver(cp);
		}
		
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", rc.getfirefoxPath());
			driver=new FirefoxDriver();
		}
		
		else if(br.equals("edge"))
		{
			System.setProperty("webdriver.edge.driver", rc.getedgePath());
			EdgeOptions cp=new EdgeOptions();
			cp.addArguments("--remote-allow-origins=*");
			driver=new EdgeDriver(cp);
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(URL);
		driver.manage().window().maximize();
	}
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	public void captureScreenshot(WebDriver driver,String tname) throws IOException
	{
		TakesScreenshot ts= (TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File target=new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		FileUtils.copyFile(src, target);
		System.out.println("Screenshot taken");
	}
	
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
}
