package com.amazon.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.amazon.utility.Page;

public class Basket {


	@FindBy(how=How.ID, using="nav-cart-count")
	static WebElement basketLink;
	
	public Basket()
	{
		PageFactory.initElements(Page.driver, this);
	}
	
	public static boolean isPresent(String phone) {
		
		basketLink.click();
		
		List<WebElement> elements = Page.driver.findElements(By.cssSelector("span.a-size-medium.sc-product-title.a-text-bold"));

		for(WebElement element : elements){
		   System.out.println("The Phones in the basket are : " + element.getText());
		   if((element.getText()).contains(phone))
		   {
			   return true;
		   }
		}
		
		return false;
	}

}
