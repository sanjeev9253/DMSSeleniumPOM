package com.holisol.DMSSelenium.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.holisol.DMSSelenium.pages.LoginPage;

public class BaseTest {

	public BasePage basepage;
	public WebDriver driver;
	public LoginPage loginpage;
	public Properties prop;
	
	
	@BeforeTest
	
	public void setUp() {
		
		basepage=new BasePage();
		prop=basepage.init_prop();
		driver=basepage.init_driver(prop);
		loginpage=new LoginPage(driver);		
		
	}
	
	@AfterTest
	
	public void tearDown() {
		driver.quit();
	}
}
	

