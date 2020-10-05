package com.holisol.DMSSelenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.holisol.DMSSelenium.base.BasePage;
import com.holisol.DMSSelenium.utils.ElementUtils;
import com.holisol.DMSSelenium.utils.JSUtils;

public class GenerateAwbPage extends BasePage {
	
	WebDriver driver;


	
	public GenerateAwbPage(WebDriver driver) {
		this.driver=driver;
		elementutils=new ElementUtils(driver);

		
	}
	
	
	
	By GenerateAwbButton=By.xpath("//a[@class='btn btn-primary']");
	By generateAwbSubtitle=By.xpath("//strong[text()='WAYBILL NUMBER']");
	
	By popUpheading=By.className("modal-title");
	
	//By selectDropdown=By.className("ui-select-placeholder text-muted ng-binding");
	By selectDropdown=By.xpath("//span[@aria-label='Select box activate']");
	By selectChoice=By.xpath("//div[contains(text(),'AMWAY (AMW763316)')]");
	
	By enterAwbCount=By.xpath("//input[@placeholder='ENTER WAYBILL NUMBER COUNT']");
	
	By awbTypeRadionButton=By.xpath("//span[contains(text(),'COD')]");
	By addNewAwbButton=By.xpath("//button[@ng-click='addawb_number()']");
	By successMessage=By.xpath("//span[contains(text(),'Waybill number generated successfully')]");
	By closeAddAwbModal=By.xpath("//button[@data-ng-show='showawb_ok']");
	
	public String verifySubTitle() {
		
		elementutils.waitForElementPresent(generateAwbSubtitle, 40);
		return elementutils.doGetText(generateAwbSubtitle);
		
	}
	
	public String goToGenerateAwbPopup() {
		elementutils.waitForUrl("awb", 5);
		elementutils.doRefresh();
		elementutils.clickWhenReady(GenerateAwbButton, 50);
		elementutils.waitForElementToBeClickable(popUpheading, 50);
		System.out.println("Open the pop up for generate awb");
		return elementutils.doGetText(popUpheading);
	}
	
	public boolean addNewAwbs() {
		
	
		elementutils.waitForElementPresent(selectDropdown, 10);
		elementutils.doClick(selectDropdown);
		elementutils.waitForElementPresent(selectChoice, 10);
		elementutils.doClick(selectChoice);
		
		//elementutils.doSelectByIndex(selectDropdown,1);
		elementutils.doSendKeys(enterAwbCount,"1");
		elementutils.doClick(awbTypeRadionButton);
		elementutils.doClick(addNewAwbButton);
		elementutils.waitForElementPresent(successMessage, 5);
		return elementutils.doIsDisplayed(successMessage);
		
		
	}
	
	public String returnTopage() {
		elementutils.doClick(closeAddAwbModal);
		return verifySubTitle();
	}
}
