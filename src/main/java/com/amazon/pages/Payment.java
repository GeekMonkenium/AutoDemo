package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.amazon.utility.Page;


public class Payment {

//	By basket = By.id("nav-cart");
//	By checkout = By.id("sc-buy-box-ptc-button");
//	
//	By fullName = By.id("enterAddressFullName");
//	By addressLine1 = By.id("enterAddressAddressLine1");
//	By phone = By.id("enterAddressPhoneNumber");
//	//By addressContinue = By.id("newShippingAddressFormFromIdentity");
//	By addressContinue = By.name("shipToThisAddress");
//	By deliverAddress = By.name("useSelectedAddress");
//	By dispatchConfirm = By.cssSelector("input.a-button-text");
//	
//	By town = By.id("enterAddressCity");
//	
//	By postCode = By.id("enterAddressPostalCode");
	
	@FindBy(how=How.ID, using="nav-cart")
	WebElement basket;
	
	@FindBy(how=How.ID, using="sc-buy-box-ptc-button")
	WebElement checkout;
	
	@FindBy(how=How.ID, using="enterAddressFullName")
	WebElement fullName;
	
	@FindBy(how=How.ID, using="enterAddressAddressLine1")
	WebElement addressLine1;
	
	@FindBy(how=How.ID, using="enterAddressPhoneNumber")
	WebElement phone;
	
	@FindBy(how=How.NAME, using="shipToThisAddress")
	WebElement addressContinue;
	
	@FindBy(how=How.NAME, using="useSelectedAddress")
	WebElement deliverAddress;
	
	@FindBy(how=How.CSS, using="input.a-button-text")
	WebElement dispatchConfirm;
	
	@FindBy(how=How.ID, using="enterAddressCity")
	WebElement town;
	
	@FindBy(how=How.ID, using="enterAddressPostalCode")
	WebElement postCode;
	
	public Payment()
	{
		PageFactory.initElements(Page.driver, this);
	}
	
	public Payment checkout()
	{
		try{
		basket.click();
		checkout.click();
		}catch(Exception e)
		{
			Page.takeScreenshot("checkout");
			throw new Error(e.getMessage());
		}
		return this;
	}
	
	public Payment setAddress(String name, String address, String city,String zip,String Phone)
	{
		try{
		fullName.sendKeys(name);
		addressLine1.sendKeys(address);
		town.sendKeys(city);
		postCode.sendKeys(zip);
		phone.sendKeys(Phone);
	
		
		//JavascriptExecutor executor = (JavascriptExecutor)Page.driver;
		//executor.executeScript("arguments[0].click();", addressContinue);
		}catch(Exception e)
		{
			throw new Error(e.getMessage());
		}
		addressContinue.click();
		
		try{
		addressContinue.click();
		}catch(Exception e1){}
		deliverAddress.click();
		try{
		deliverAddress.click();
		}catch(Exception e2){}

		return this;
		
	}
	
	public void doDispatch()
	{
		dispatchConfirm.click();
		try{
			dispatchConfirm.click();
		}catch(Exception e){}
		
	}
}
