package com.holisol.DMSSelenium.pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.holisol.DMSSelenium.base.BasePage;
import com.holisol.DMSSelenium.utils.ElementUtils;


public class HomePage extends BasePage {
	
	WebDriver driver;
	Properties prop;
	
	String burl="";
	
	HttpURLConnection huc = null;
    int respCode = 200;
	
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		elementutils=new ElementUtils(this.driver);
	}
	
	By dashboard=By.xpath("//span[contains(text(),'DASHBOARD')]");
	
	By links=By.tagName("a");
	
	By awb=By.xpath("//span[text()='GENERATE AWB NUMBER']");
	
	
	public String getHomePageTitle() {
		
		elementutils.waitForElementPresent(dashboard, 40);
		
		return elementutils.doGetText(dashboard);
		//return driver.findElement(dashboard).getText();
	}
	
	public WebElement getAllLinksLoading() {
		 return elementutils.waitForElementPresent(awb, 40);
	}
	
	public List<WebElement> checkForBrokenLink() {
		
		return elementutils.getElements(links);
			
	}
	
	/*public void checkForValidURL() {
		
		
		List<WebElement> URLS=elementutils.getElements(links);
		
        Iterator<WebElement> it = URLS.iterator();
        
        while(it.hasNext()){
            
        	burl = it.next().getAttribute("href");
            
            System.out.println(burl);
        
            if(burl == null || burl.isEmpty()){
              System.out.println("URL is either not configured for anchor tag or it is empty");
                continue;
            }
            
            if(!burl.startsWith(prop.getProperty("url"))){
                System.out.println("URL belongs to another domain, skipping it.");
                continue;
            }
            
            try {
                huc = (HttpURLConnection)(new URL(burl).openConnection());
                
                huc.setRequestMethod("HEAD");
                
                huc.connect();
                
                respCode = huc.getResponseCode();
                
                if(respCode >= 400){
                    System.out.println(burl+" is a broken link");
                }
                else{
                    System.out.println(burl+" is a valid link");
                }
                    
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }        }
	}*/
	
	public void BrokenLinks() throws IOException {
		
		
		List<WebElement> applinks= elementutils.getElements(links);
		System.out.println("Total no. of links are:" + applinks.size());
		
		List<WebElement> activelink=new ArrayList<WebElement>();
		
		for(int i=0;i<applinks.size();i++) {
			
			if(applinks.get(i).getAttribute("href")!=null ) {
				activelink.add(applinks.get(i));
				
			}
			
		}
		
		System.out.println("Total active links with href are" + activelink.size() );
		
		for(int j=0;j<activelink.size();j++) {
			HttpURLConnection connection=(HttpURLConnection)new URL(activelink.get(j).getAttribute("href")).openConnection();
			
			connection.connect();
			
			
			String response=connection.getResponseMessage();
			connection.disconnect();
			
			System.out.println(activelink.get(j).getAttribute("href") + ">>>>>"+response);
			}
		
	}
	
	
	public GenerateAwbPage goToGenerateAwbPage() {
		
		elementutils.clickWhenReady(awb, 15);
		
		return new GenerateAwbPage(driver);
		
	}
	

}
