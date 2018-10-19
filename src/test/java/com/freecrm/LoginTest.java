package com.freecrm;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.generic.Base;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class LoginTest extends Base{
	
	private static AndroidDriver<?> androidDriver;

	private static Properties prop;
	
	@BeforeMethod
	public void setUP() throws MalformedURLException {
		prop = loadConfigProperties();
		androidDriver = setCapabilitiesForWeb(prop.getProperty("deviceName"), prop.getProperty("plateformName"),
				prop.getProperty("plateformVersion"), prop.getProperty("appPackage"), prop.getProperty("appActivity"),
				"Chrome");

		androidDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		androidDriver.manage().deleteAllCookies();

		androidDriver.get("https://www.ebay.com/");
	}

	@AfterMethod
	public void tearDown() {
		androidDriver.quit();
	}
	
	@Test(enabled=true)
	public void loginTest() {
		androidDriver.findElement(By.xpath("//a[@id=\'gh-mebay\']")).click();
		
		androidDriver.findElement(By.id("userid")).sendKeys("dheerendras06@gmail.com");
		androidDriver.findElement(By.xpath("//input[@id=\'pass\']")).sendKeys("test@123");
		androidDriver.findElement(By.id("sgnBt")).click();
	}
	
	@Test(enabled=false)
	public void registrationTest() {
		
		androidDriver.findElement(By.xpath("//a[@id=\'gh-mebay\']")).click();
		
		androidDriver.findElement(By.xpath("//div[@id=\'regTab\']")).click();
		
		androidDriver.findElement(By.id("firstname")).sendKeys("Dheerendra");
		androidDriver.findElement(By.id("lastname")).sendKeys("Singh");
		androidDriver.findElement(By.id("email")).sendKeys("dheerendras06@gmail.com");
		androidDriver.findElement(By.id("PASSWORD")).sendKeys("test@1oct");
		
		androidDriver.findElement(By.id("ppaFormSbtBtn")).click();
		
	}
	
	@Test
	public void clickOnHelpTest() {
		
		((JavascriptExecutor) androidDriver).executeScript("window.scrollBy(0,1690)", "");
		
		TouchAction action=new TouchAction(androidDriver);
		/*MobileElement element=(MobileElement) androidDriver.findElementById("");
		action.longPress(null);*/
		
		/*Dimension dimension = androidDriver.manage().window().getSize();

		System.out.println(dimension);
        for (int i=0; i<=5;i++){

            int start = (int) (dimension.getHeight() * 0.8);
            int end = (int) (dimension.getHeight() * 0.1);
            int x = (int) (dimension.getWidth()*.5);

            //androidDriver.swipe(x, start, x, end, 30);
        }*/
	}
	
	
	public static boolean swipeDown() {

		
		try {
			JavascriptExecutor js = (JavascriptExecutor) androidDriver;
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("direction", "down");
			js.executeScript("mobile: scroll", scrollObject);
			System.out.println("Swipe down was Successfully done");
		} catch (Exception e) {
			System.out.println("swipe down was not successfull");
		}
		return false;
	}

	

}
