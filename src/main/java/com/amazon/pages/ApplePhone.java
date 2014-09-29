package com.amazon.pages; 

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.amazon.utility.Page;


public class ApplePhone {
	
	@FindBy(how=How.LINK_TEXT, using="Next Page")
	WebElement next;
	
	@FindBy(how=How.CSS, using="span.pagnDisabled")
	WebElement pageCount;
	
	@FindBy(how=How.ID, using="add-to-cart-button")
	WebElement addToBasket5;
	
	@FindBy(how=How.ID,using="bb_atc_button")
	WebElement addToBasket;

	@FindBy(how=How.ID, using="quantity")
	WebElement phoneQuantity;
	
	public ApplePhone()
	{
		PageFactory.initElements(Page.driver, this);
	}
	
	public ApplePhone getNextPage()
	{
		try{
		next.click();
		}catch(NoSuchElementException e)
		{
			throw new Error(e.getMessage());
		}
		return new ApplePhone();
	}
	
	public void getPhone(String phone)
	{

		boolean isPhonefound = false;
		
		int noOfPages = Integer.parseInt(pageCount.getText());
		
		System.out.println("Total number of pages : " + noOfPages);
		
		for (int i = 0 ; i< noOfPages ; i++)
		{
			List<WebElement> elements = Page.driver.findElements(By.cssSelector("span.lrg.bold"));
		
			for(WebElement element : elements)
			{
				System.out.println(element.getText());
				if(element.getText().contains(phone))
				{
					isPhonefound = true;
					element.click();
					break;
				}
						
			}
			
			if(!isPhonefound)
				this.getNextPage();
			break;
			
		}

	}
	
	public void addToBasket()
	{
		addToBasket.click();
	}
	
	public void addToBasket5()
	{
		addToBasket5.click();
	}
	
	public void buyIPhone(String phone) throws InterruptedException
	{
		getPhone(phone);
		addToBasket();
		

	}

	public void buyIPhoneUsingSearch(String phone, int phones) throws InterruptedException {
		
	
		List<WebElement> elements = Page.driver.findElements(By.cssSelector("span.lrg.bold"));
		
		for(WebElement element : elements)
		{
			System.out.println(element.getText());
			if(element.getText().contains(phone))
			{
				element.click();
				break;
			}
					
		}
		
		Select phoneDD = new Select(phoneQuantity);
		phoneDD.selectByValue(Integer.toString(phones));
		addToBasket();
		
	}

	public void buyIPhone5(String phone) {
		// TODO Auto-generated method stub
		getPhone(phone);
		addToBasket5();
		
	}

}
