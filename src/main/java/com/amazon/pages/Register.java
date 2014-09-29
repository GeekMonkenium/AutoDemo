package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.amazon.utility.Page;


public class Register {

	@FindBy(how=How.ID, using="nav-your-account")
	WebElement account;

	@FindBy(how=How.ID, using="ap_email")
	WebElement signInEmail;

	@FindBy(how=How.ID, using="ap_signin_create_radio")
	WebElement newUserCheck;
	
	@FindBy(how=How.ID, using="signInSubmit-input")
	WebElement secureServerSignIn;
	
	@FindBy(how=How.ID, using="ap_customer_name")
	WebElement regUserName;
	
	@FindBy(how=How.ID, using="ap_email_check")
	WebElement regEmail;
	
	@FindBy(how=How.ID, using="ap_password")
	WebElement regPassword1;
	
	@FindBy(how=How.ID, using="ap_password_check")
	WebElement regPassword2;
	
	@FindBy(how=How.ID, using="continue-input")
	WebElement createAccount;
	
	private String registerSuccess;
	
	public Register()
	{
		PageFactory.initElements(Page.driver, this);
	}
	
	public Register goRegister()
	{
		account.click();
		return this;
	}
	
	public Register setSignIn(String email)
	{
		signInEmail.sendKeys(email);
		newUserCheck.click();
		secureServerSignIn.click();
		return this;
	}
	
	public Register setRegister(String UserName, String email, String Password)
	{
		regUserName.sendKeys(UserName);
		regEmail.sendKeys(email);
		regPassword1.sendKeys(Password);
		regPassword2.sendKeys(Password);
		return this;
	}
	
	public void Register()
	{
		createAccount.click();
	}
	
	public static Boolean isRegisterSuccess()
	{
		return "GeekMonkey".equals(Page.driver.findElement(By.id("nav-signin-text")).getText());
	}
	
	
	
	

}
