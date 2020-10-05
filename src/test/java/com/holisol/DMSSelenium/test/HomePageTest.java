package com.holisol.DMSSelenium.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.holisol.DMSSelenium.base.BaseTest;
import com.holisol.DMSSelenium.pages.HomePage;
import com.holisol.DMSSelenium.utils.Constants;

public class HomePageTest extends BaseTest {
	
	HomePage homepage;

	
	
	@BeforeClass
	
	public void homePagesetUp() {
		
		homepage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}

	
	@Test
	public void verifyHomePageTitle() {
		String stitle=homepage.getHomePageTitle();
		Assert.assertEquals(stitle, Constants.home_page_subtitle, "title not present");
	}
	
	@Test
	
	public void verifyBrokenLinks() throws IOException {
		
		homepage.getAllLinksLoading();
		homepage.BrokenLinks();
	}
	

}
