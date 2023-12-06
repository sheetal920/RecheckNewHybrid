package com.HybridFrameworkStructure.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
// this class is same like pageobject class , like Properties ka pro object create kia same as WebDriver driver , then constructor invoke then jo jo value config.properties me hai unke liye action method create krenge
public class Readconfig {

	Properties pro;
	
	public Readconfig() //constructor 
	{
		File src=new File("./ConfigurationFiles/config.properties");
		try
		{
			FileInputStream fis=new FileInputStream(src); //jb bhi koi file read mode me open krni ho to FileInputStream use krenge 
		pro=new Properties();
		pro.load(fis);
		}
		catch(Exception e)
		{
			System.out.println("Exception is"+e.getMessage());
		}	
	}
	public String getApplicationURL()
	{
		String url=pro.getProperty("BaseURL");
		return url;
		
	}
	public String getchromePath()
	{
		String chromePath=pro.getProperty("chromepath");
		return chromePath;
		
	}
	public String getfirefoxPath()
	{
		String firefoxPath=pro.getProperty("firefoxpath");
		return firefoxPath;
		
	}
	public String getedgePath()
	{
		String edgePath=pro.getProperty("edgepath");
		return edgePath;
		
	}
	public String getUser()
	{
		String user=pro.getProperty("username");
		return user;
		
	}
	public String getPassword()
	{
		String password=pro.getProperty("password");
		return password;
		
	}
}
