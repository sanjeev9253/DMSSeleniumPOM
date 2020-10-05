package com.holisol.DMSSelenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.holisol.DMSSelenium.base.BasePage;
import com.holisol.DMSSelenium.utils.Constants;
import com.holisol.DMSSelenium.utils.ElementUtils;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {
	
	private WebDriver driver;
	
	//By Locators --OR
	
	By email=By.xpath("//input[@type='email']");
	By password=By.xpath("//input[@type='password']");
	By loginbtn=By.xpath("//input[@type='button']");
	
	//Const. to int driver from base class
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		elementutils= new ElementUtils(this.driver);
	}
	
	@Step("check login page title")
	public String getLoginPageTitle() {
		
		return elementutils.waitForTitleToBePresent(Constants.login_page_title, 10);
		//return driver.getTitle();
	}

	@Step("Login to DMS app")
	public HomePage doLogin(String email,String password) {
		
		System.out.println("browser is launched");
		
		elementutils.waitForElementPresent(this.email, 5);

		
		System.out.println("Entering username");
		
		elementutils.doSendKeys(this.email, email);
		
		System.out.println("Entering password");
		
		elementutils.doSendKeys(this.password, password);
		
		elementutils.doClick(loginbtn);	
			
		System.out.println("Clicking on login button and taking user to Homepage");
		
		return new HomePage(driver);
		
	}
}
