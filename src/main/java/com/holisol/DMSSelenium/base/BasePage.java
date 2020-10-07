package com.holisol.DMSSelenium.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;


import com.holisol.DMSSelenium.utils.ElementUtils;
import com.holisol.DMSSelenium.utils.OptionManager;

import freemarker.log.Logger;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	WebDriver driver;
	protected Properties prop;
	public ElementUtils elementutils;
	public static ThreadLocal <WebDriver> tdriver=new ThreadLocal<WebDriver>();
	OptionManager optionmanager;
	
	public static synchronized WebDriver getDriver() {
		return tdriver.get();
	}
	

	
	public WebDriver init_driver(Properties prop) {
		
		OptionManager optionmanager=new OptionManager(prop);
		String browserName= prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().version("2.40").setup();
			//driver=new ChromeDriver();
			tdriver.set(new ChromeDriver(optionmanager.getChromeOptions()));
		}
		
		else if (browserName.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			//driver=new FirefoxDriver();
			tdriver.set(new FirefoxDriver(optionmanager.getFirefoxOptions()));
		}
		
		getDriver().manage().window().maximize();
		
		try
	    {
			getDriver().get(prop.getProperty("url"));
	    }
	    catch(Exception e)
	    {
	        Reporter.log("network server is slow..check internet connection");
	       // Logger.info("Unable to open the website")
	        System.out.println("site is not working");
	        
	        
	        throw new Error("network server is slow..check internet connection");
	    }
		
		
		
		return getDriver();
		
		
	}
	
	
	//used for initialize the properties
	public Properties init_prop() {
		
		
		
		prop=new Properties();
		String path=null;
		String env=null;
		
		try {
			
			env=System.getProperty("env");
			
			if(env==null) {
				path="./src/main/java/com/holisol/DMSSelenium/config/config.properties";
			}
			else {
				switch(env) {
				case "qa":
					path="./src/main/java/com/holisol/DMSSelenium/config/config.properties";
					break;
					
					default:
					System.out.println("please pass the correct env variable");
					break;
					
				}
			}
			
			FileInputStream ip=new FileInputStream(path);
			
			prop.load(ip);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		return prop;
		

		
		
		
	}
	
	//Take screenshot in selenium
	
	public String getScreenshot() {
		File src=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
		
		File destination=new File(path);
		
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
	}

	
	
	
}
