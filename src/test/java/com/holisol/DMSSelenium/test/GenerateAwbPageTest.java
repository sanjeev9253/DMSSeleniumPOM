package com.holisol.DMSSelenium.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.holisol.DMSSelenium.base.BaseTest;
import com.holisol.DMSSelenium.pages.GenerateAwbPage;
import com.holisol.DMSSelenium.pages.HomePage;
import com.holisol.DMSSelenium.utils.Constants;

public class GenerateAwbPageTest extends BaseTest {
	
	HomePage homepage;
	GenerateAwbPage genawbpage;
	
	@BeforeClass
	
	public void generateAwbPageSetUp() {
		homepage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		genawbpage= homepage.goToGenerateAwbPage();
		
	}
	
	@Test
	public void verifyGenAwbPageSubs() {
		 String subtitle=genawbpage.verifySubTitle();
		 Assert.assertEquals(subtitle, Constants.Generate_page_subtitle);
	}
	
	@Test 
	
	public void goToGenerateAwbPageTest() {
		
		String tilte=genawbpage.goToGenerateAwbPopup();
		Assert.assertEquals(tilte, Constants.Generate_popup_subtitle);
				
	}
	
	@Test
	public void verifyAddNewAwbTest() {
		boolean flag=genawbpage.addNewAwbs();
		Assert.assertTrue(flag);
		String title=genawbpage.returnTopage();
		Assert.assertEquals(title, Constants.Generate_page_subtitle);
		
		}
	

}
