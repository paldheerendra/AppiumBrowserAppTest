package com.chrome.test;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.generic.Base;

import io.appium.java_client.android.AndroidDriver;
import junit.framework.Assert;

public class ElevateTest extends Base {

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

		androidDriver.get("https://www.elevate.com/");
	}

	@AfterMethod
	public void tearDown() {
		androidDriver.quit();
	}

	@Test(enabled = true)
	public void titleTest() {
		System.out.println("Title:>>>>>" + androidDriver.getTitle());

	}

	@Test(enabled = true)
	public void menuTest() {
		// goToMenu
		
		androidDriver.findElement(By.id("goToMenu")).click();
		System.out.println("Menus are Visible..");
		
		System.out.println("Printing all the Menu options....");
		List<WebElement> list = (List<WebElement>) androidDriver.findElements(By.xpath("//div[@id=\'footer\']//ul//a"));

		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getText());		
		}
	}

	@Test(enabled = true)
	public void contactUsPageTest() {
		WebElement menuButton = androidDriver.findElement(By.id("goToMenu"));
		// Contact Us

		Point location = menuButton.getLocation();
		menuButton.click();

		System.out.println(location);
		System.out.println("Opening Contact Us page.....");

		List<WebElement> list = (List<WebElement>) androidDriver.findElements(By.xpath("//div[@id=\'footer\']//ul//a"));

		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getText());

			if (list.get(i).getText().contains("Contact Us")) {
				list.get(i).click();
				break;
			}
		}

		Assert.assertEquals(androidDriver.getTitle(), "Contact Us - Elevate");

	}

	@Test(enabled = true)
	public void aboutPageTest() {
		WebElement menuButton = androidDriver.findElement(By.id("goToMenu"));
		menuButton.click();
		
		System.out.println("Opening About US page..........");

		List<WebElement> list = (List<WebElement>) androidDriver.findElements(By.xpath("//div[@id=\'footer\']//ul//a"));

		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getText().contains("About Us")) {
				list.get(i).click();
				break;
			}
		}

		swipeDown();

		Assert.assertEquals(androidDriver.getTitle(), "About Us - Elevate");

	}

	@Test(enabled = true)
	public void policyTest() {
		WebElement menuButton = androidDriver.findElement(By.id("goToMenu"));
		menuButton.click();
		System.out.println("Scrolling down to check the Policy page is visible.....");
		
		((JavascriptExecutor) androidDriver).executeScript("window.scrollBy(0,390)", "");
		
		System.out.println("Clicking on Privacy Policy");

		List<WebElement> list = (List<WebElement>) androidDriver.findElements(By.xpath("//div[@id=\'footer\']//ul//a"));

		WebElement element = null;
		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getText().contains("Privacy Policy")) {
				element = list.get(i);
				element.click();
				break;
			}
		}

		

		// ((JavascriptExecutor)
		// driver).executeScript("arguments[0].scrollIntoView(true);", element);

		// [0,756][720,855]
		// [0,430][720,529]

		// TouchActions action = new TouchActions(androidDriver);

		// action.up(0, 430).build().perform();
		// action.scroll(element, 0, 400);
		// action.longPress(element).move(0, 400).build().perform();
		// action.perform();

		System.out.println("Policy Tab is visible on screen");
	}

	public static boolean swipeDown() {

		WebElement menuButton = androidDriver.findElement(By.id("goToMenu"));
		menuButton.click();
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

	@Test(enabled = false)
	public void tochTest() {
		Dimension size = androidDriver.manage().window().getSize();
		System.out.println(size);

		// Find x1 point which is at right side of screen.
		int x1 = (int) (size.width * 0.20);

		System.out.println(x1);
		// Find x2 point which is at left side of screen.
		int x2 = (int) (size.width * 0.80);
		System.out.println(x2);

		int y1 = (int) (size.height * 0.20);
		int y2 = (int) (size.height * 0.80);

	}

}
