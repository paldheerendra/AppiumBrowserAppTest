package com.generic;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class Base {

	static AndroidDriver<?> androidDriver;
	private static Properties prop;
	
	
	public static AndroidDriver<?> setCapabilitiesForWeb(String deviceName, String plateformName, String plateformVersion,
			String appPackage, String appActivity, String browser) throws MalformedURLException
	{

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("deviceName", deviceName);
		dc.setCapability("platformName", plateformName);
		dc.setCapability("platformVersion", plateformVersion);
		dc.setCapability("appPackage", appPackage);
		dc.setCapability("appActivity", appActivity);
		
		dc.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

		//dc.setCapability("browserName", browser);
		androidDriver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);

		return androidDriver;

	}
	
	public static AndroidDriver<?> initialSetUp() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("deviceName", "RGLZZDGUR8ZPIFLB");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "6.0");
/*
		capabilities.setCapability("appPackage", "com.android.chrome");
		capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");*/

		capabilities.setCapability("browserName", "Chrome");

		androidDriver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

		return androidDriver;
	}
	
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

	
}
