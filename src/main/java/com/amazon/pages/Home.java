package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.amazon.utility.Page;


public class Home extends Page{

	@FindBy(how=How.ID, using="nav-shop-all-button")
	WebElement department;
	
	@FindBy(how=How.ID, using="twotabsearchtextbox")
	WebElement search;
	
	@FindBy(how=How.CSS, using="input.nav-submit-input")
	WebElement searchGo;
	
	public Home()
	{
		PageFactory.initElements(Page.driver, this);
	}
	
	// Retrieves Department Page
	public Department gotoDepartment(){
		try{
		department.click();
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
			PageFactory.initElements(Page.driver, Department.class);
			return new Department();
	}
	
	public ApplePhone DoSearchIPhone(String iPhone)
	{
		try{
		search.sendKeys(iPhone);
		searchGo.click();
		}catch(NoSuchElementException e)
		{
			e.printStackTrace();
		}
			return new ApplePhone();
	}
	
	public Register Registration()
	{
		return new Register();
	}
	
	public Payment makePayment()
	{
		return new Payment();
	}
}
