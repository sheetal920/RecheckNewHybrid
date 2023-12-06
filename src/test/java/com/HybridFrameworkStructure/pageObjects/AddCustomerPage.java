package com.HybridFrameworkStructure.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage{
	WebDriver driver;
	
	public AddCustomerPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
@FindBy(how=How.XPATH, using="//ul[@class='menusubnav']/li[2]")
WebElement clickAddCustomer;

@FindBy(how=How.CSS, using="input[onkeyup*='customername']")
WebElement enterCustomerName;

@FindBy(how=How.XPATH, using="//input[@name='rad1'][2]")
WebElement gender;

@FindBy(how=How.ID_OR_NAME, using="dob")
WebElement txtdob;

@FindBy(how=How.CSS, using="[onkeyup*='Address']")
WebElement address;

@FindBy(how=How.CSS, using="[onkeyup*='City']")
WebElement city;

@FindBy(how=How.CSS, using="[onkeyup*='State']")
WebElement state;

@FindBy(how=How.CSS, using="[onkeyup*='PIN']")
WebElement pin;

@FindBy(how=How.CSS, using="[onkeyup*='Telephone']")
WebElement mobile;

@FindBy(how=How.NAME, using="emailid")
WebElement emailid;

@FindBy(how=How.NAME, using="password")
WebElement password;

@FindBy(how=How.XPATH, using="//input[@type='submit']")
WebElement submit;

public void clickAddCustomer()
{
	clickAddCustomer.click();
}
public void custName(String cname)
{
	enterCustomerName.sendKeys(cname);
}

public void gend()
{
	gender.click();
}
public void Dob(String mm,String dd,String yy)
{
	txtdob.sendKeys(mm);
	txtdob.sendKeys(dd);
	txtdob.sendKeys(yy);
}

public void Address(String add)
{
	address.sendKeys(add);
}

public void City(String ct)
{
     city.sendKeys(ct);
}
public void State(String st)
{
	state.sendKeys(st);
}
public void PIN(int pn)
{
	pin.sendKeys(String.valueOf(pn)); //PIN interger me agr waha se pass kia to send key int accept nhi karti only character or string accept karti hai
}
public void Mobile(String mb)
{
	mobile.sendKeys(mb);
}

public void custemail(String eid)
{
	emailid.sendKeys(eid);
}


public void Password(String pwd)
{
	password.sendKeys(pwd);
}
public void submit()
{
	submit.click();
}
}
