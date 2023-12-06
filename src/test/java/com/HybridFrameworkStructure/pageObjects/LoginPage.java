package com.HybridFrameworkStructure.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
WebDriver driver;
public LoginPage(WebDriver driver)
{
	this.driver=driver; // or agr driver name diff hai to ldriver=rdriver; direct use kar skte hai
	PageFactory.initElements(driver, this);
}
@FindBy(xpath="//input[@type='text']")
WebElement loginfield;

@FindBy(xpath="//input[@type='password']")
WebElement passwordfield;

@FindBy(name="btnLogin")
WebElement submitClickButton;

@FindBy(linkText="Log out")
WebElement logout;

public void setUserName(String username)
{
	loginfield.sendKeys(username);
}

public void setPassword(String pwd)
{
	passwordfield.sendKeys(pwd);
}
public void submitClick()
{
	submitClickButton.click();
}
public void LogOutClick()
{
	logout.click();
}
}
