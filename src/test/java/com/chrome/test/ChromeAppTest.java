package com.chrome.test;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.generic.Base;

import io.appium.java_client.android.AndroidDriver;

public class ChromeAppTest extends Base {

	static AndroidDriver<?> androidDriver;

	private static Properties prop;

	public static Properties loadConfigProperties() {
		prop = new Properties();
		try {

			prop.load(
					new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/configBase.properties"));

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return prop;
	}

	@BeforeMethod
	public void setUP() throws MalformedURLException {
		prop = loadConfigProperties();
		String deviceName = prop.getProperty("deviceName");
		String plateformName = prop.getProperty("plateformName");
		String plateformVersion = prop.getProperty("plateformVersion");

		String appPackage = prop.getProperty("appPackage");
		String appActivity = prop.getProperty("appActivity");
		String browser = "Chrome";

		androidDriver = setCapabilitiesForWeb(deviceName, plateformName, plateformVersion, appPackage, appActivity,
				browser);
		
		androidDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		androidDriver.manage().deleteAllCookies();
		
		androidDriver.get("https://www.google.com/");
		
		
	}

	/*
	  @BeforeMethod public void setup1() throws MalformedURLException {	
	  androidDriver = initialSetUp();
	  }
	 */

	@AfterMethod
	public void tearDown() {

		androidDriver.quit();
	}

	@Test(priority=0)
	public void launchWebTest() {

		System.out.println("Launched Google.com successfully");
	}

	@Test(priority=2)
	public void googleSearchTest() {
		androidDriver.findElement(By.name("q")).sendKeys("SQS India");
		//androidDriver.findElement(By.xpath("//input[@name='btnK']")).click();

	}
	
	@Test(priority=1)
	public void swipDown() throws InterruptedException {
				
		androidDriver.findElement(By.name("q")).sendKeys("SQS India");
		//androidDriver.findElement(By.name("q")).click();
		String googleSearchXpath="//android.widget.Button[@text()=\'Google Search\' and @index=\'1\']";
		System.out.println("Search Button Xpath");
		//androidDriver.findElement(By.xpath(googleSearchXpath)).click();
		
		
		
		
		
	}

}
