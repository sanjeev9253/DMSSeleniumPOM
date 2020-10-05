package com.holisol.DMSSelenium.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.holisol.DMSSelenium.base.BaseTest;
import com.holisol.DMSSelenium.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

//@Epic ("Jira ticket number")
@Story("Jira ticket number")
public class LoginPageTest extends BaseTest {
	


	
	@Test
	@Description("verify the login page title ")
	@Severity(SeverityLevel.NORMAL)
	
	public void verifyLoginTitleTest() {
		String title=loginpage.getLoginPageTitle();
		Assert.assertEquals(title, Constants.login_page_title);
			
	}
	
	@Test
	@Description("login to app")
	@Severity(SeverityLevel.CRITICAL)
	
	public void doLoginTest() {
		loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
}
	

