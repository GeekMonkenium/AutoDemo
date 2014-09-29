package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.amazon.utility.Page;

public class Department {
	
	@FindBy(how=How.LINK_TEXT,using="Phones & Accessories")
	WebElement phoneAccessLink;
	
	public Department()
	{
		PageFactory.initElements(Page.driver, this);
	}
	
	public PhonesAccessories getPhoneAccessories()
	{
		try{
			phoneAccessLink.click();
		}
		catch(Exception e)
		{
			throw new Error(e.getMessage());
		}
		PageFactory.initElements(Page.driver, PhonesAccessories.class);
		return new PhonesAccessories();
	}
}
