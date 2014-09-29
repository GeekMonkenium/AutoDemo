package com.amazon.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.amazon.utility.Page;


public class PhonesAccessories {

	@FindBy(how=How.LINK_TEXT, using="Apple iPhone")
	WebElement appleIphone;
	
	public PhonesAccessories()
	{
		PageFactory.initElements(Page.driver, this);
	}
	
	
	public ApplePhone buyApplePhone()
	{
		// click Apple iPhone
		try{
			appleIphone.click();
		}catch(NoSuchElementException e)
		{
			throw new Error(e.getMessage());
		}
		PageFactory.initElements(Page.driver, ApplePhone.class);
		return new ApplePhone();
	}
}
