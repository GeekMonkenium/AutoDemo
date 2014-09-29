package com.amazon.utility;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;

import com.amazon.pages.Basket;
import com.amazon.pages.Home;
import com.amazon.pages.Payment;
import com.amazon.pages.Register;

public class Page {
	
	public static WebDriver driver = null;
	public static Properties CONFIG =null;
	public static Properties OR =null;
	public static Home home=null;
	public static boolean isLoggedIn=false;
	public static Register register = null;
	public static Payment payment = null;
	public static Logger logger = null;

	public Page(){
		
		logger = Logger.getLogger(Page.class);
		logger.setLevel(Level.INFO);
		BasicConfigurator.configure();
		
		if(driver==null){
		// initialize the properties file
		CONFIG= new Properties();
		OR = new Properties();
		try{
			// config
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\config.properties");
			CONFIG.load(fs);

		}catch(Exception e){
				// error
				return;
		}
		
		System.out.println(CONFIG.getProperty("browser"));
		if(CONFIG.getProperty("browser").equals("Mozilla"))
			  this.driver=new FirefoxDriver();
		else if(CONFIG.getProperty("browser").equals("IE"))
		  this.driver=new InternetExplorerDriver();
		else if(CONFIG.getProperty("browser").equals("Chrome")){
			this.driver=new ChromeDriver();
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		home = new Home();
	
		register = home.Registration();
	
		payment = home.makePayment();
		PageFactory.initElements(Page.driver, Basket.class);
	
	}
		
}

	
	// click
	public void click(String xpathKey){
		try{
	        driver.findElement(By.xpath(OR.getProperty(xpathKey))).click();
		}catch(Exception e){
			System.out.println("error");
			e.printStackTrace();
			// report error
		}
	}
	// input
	public void input(String xpathKey, String text){
		try{
		driver.findElement(By.xpath(OR.getProperty(xpathKey))).sendKeys(text);
		}catch(Exception e){
			// report error
			e.printStackTrace();
		}
	}
			// select
	// verification
	public boolean isElementPresent(String xpathKey){
		try{
			driver.findElement(By.xpath(OR.getProperty(xpathKey)));
		}catch(Exception e){
			return false;
		}
		
		return true;
	}			
	// finds the link on page
	public boolean isLinkPresent(String linkText){
		try{
			driver.findElement(By.linkText(linkText));
		}catch(Exception e){
			return false;
		}
		
		return true;
	}
	
	
	public static void takeScreenshot(String fileName) {

		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\screenshots\\"+fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
	}
	
	
	
	
	
	
}
