package com.amazon.tests;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.amazon.pages.ApplePhone;
import com.amazon.pages.Basket;
import com.amazon.pages.Department;
import com.amazon.pages.PhonesAccessories;
import com.amazon.pages.Register;
import com.amazon.utility.Page;

@RunWith(Parameterized.class)
public class TestPhone extends Page {
	Page page = null;
	ApplePhone applePhone = null;
	Department department = null;
	PhonesAccessories phonesAccessories = null;
	
	@Before
	public void Init()
	{
		page.driver.navigate().to("http://www.amazon.co.uk");
	}
	
	@After
	public void CleanUp()
	{
		driver.close();
		driver = null;
		
	}
	
	private String username;
	private String password;
	private String phone1;
	private String phone2;
	private String phone3;
	private String email;
	private String address;
	private String city;
	private String postCode;
	private String phone;
	
	@Parameters
    public static Collection SpreadsheetData() throws IOException {
        InputStream spreadsheet = new FileInputStream("D:\\CTS\\AmazonMaven\\src\\main\\resources\\TestData.xlsx");
        return new com.amazon.utility.SpreadsheetData(spreadsheet).getData();
    }
	
	public TestPhone(String username, String password,String phone1, String phone2, String phone3, String email, String address, String city, String postCode, String contact)
	{
		super();
		this.username = username;
		this.password = password;
		this.phone1 = phone1;
		this.phone2=phone2;
		this.phone3=phone3;
		this.email=email;
		this.address=address;
		this.city=city;
		this.postCode=postCode;
		this.phone=contact;
		
	}
	
	
	public void TestBasket() throws InterruptedException
	{
		logger.info("Running Buy iPhone Test");
		home.gotoDepartment().getPhoneAccessories().buyApplePhone().buyIPhone(phone1);
		assertTrue(Basket.isPresent(phone1));
		
		logger.info("Running Buy iPhone Test");
		home.gotoDepartment().getPhoneAccessories().buyApplePhone().buyIPhone(phone2);
		assertTrue(Basket.isPresent(phone2));

	}
	
	@Test
	public void BuyIPhonePOM() throws InterruptedException
	{

		
		// Select First phone
		Page.driver.navigate().to(CONFIG.getProperty("landingPage"));
		
		logger.info("Running Buy iPhone Test");
		home.gotoDepartment().getPhoneAccessories().buyApplePhone().buyIPhone(phone1);
		assertTrue(Basket.isPresent(phone1));
		
		
		// Select Second Phone
		Page.driver.navigate().to(CONFIG.getProperty("landingPage"));
		
		logger.info("Running Buy iPhone Test");
		home.gotoDepartment().getPhoneAccessories().buyApplePhone().buyIPhone(phone2);
		assertTrue(Basket.isPresent(phone2));
		
		// Select Third Phone
		Page.driver.navigate().to(CONFIG.getProperty("landingPage"));
		home.DoSearchIPhone(phone3)
		  .buyIPhoneUsingSearch(phone3,3);
		
		assertTrue(Basket.isPresent(phone3));
		
		Page.driver.navigate().to(CONFIG.getProperty("landingPage"));
		
		register.goRegister()
			.setSignIn(email)
			.setRegister(username, email, password)
			.Register();
		
		assertTrue(Register.isRegisterSuccess());
		
		payment.checkout()
			.setAddress(username, address, city, postCode,phone)
			.doDispatch();
		
		assertTrue("Amazon Checkout Successful", true);
		
	}

}
