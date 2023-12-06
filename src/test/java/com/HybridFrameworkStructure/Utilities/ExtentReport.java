package com.HybridFrameworkStructure.Utilities;

import java.io.File;
import java.io.IOException;

//Extent report 5.x

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

//This is a Listener Class used to generate Extent Report
public class ExtentReport implements ITestListener //TestListenerAdapter: ye bhi implements kar skte hai
{
	public ExtentSparkReporter sparkReporter; //specify the location of report
	public ExtentReports extent; //will attach sparkReporter with extent then what details should be populated in the report
	public ExtentTest test;//
// in extent report we have 2 part, 1. is env and configuration(this will done with the help of extent obj) 2. test part((this will done with the help of test object))	
	String repName;
	
	public void onStart(ITestContext testContext)
	{		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		repName="Test-Report-"+timeStamp+".html";
				
		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "/test-output/" +repName);//specify location of the report
		try {
			sparkReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		sparkReporter.config().setDocumentTitle("Banking Test Project"); // Title of report
		sparkReporter.config().setReportName("Functional Test report"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);
				
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("hostname", "localhost");
		extent.setSystemInfo("Environemnt","QA");
		extent.setSystemInfo("user","pavan");
	}
	
		
	public void onTestSuccess(ITestResult tr)
	{
		test=extent.createTest(tr.getName()); //create new entry in the report
		test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));//send the passed info
	
	}
	
	public void onTestFailure(ITestResult tr)
	{
		test=extent.createTest(tr.getName()); 
		test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));//send the passed info
		
		String schreenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
	    File f=new File(schreenshotPath);
	    if(f.exists())
	    {
	    	try
	    	{
	    		test.fail("Screenshot is below:"+test.addScreenCaptureFromPath(schreenshotPath));
	    	}
	    	catch (Exception e)
	    	{
	    		e.printStackTrace();
	    	
	    	}
	    }
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		test=extent.createTest(tr.getName()); 
		test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
	
}
