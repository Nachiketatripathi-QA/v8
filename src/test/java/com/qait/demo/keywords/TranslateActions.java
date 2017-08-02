package com.qait.demo.keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;

public class TranslateActions extends GetPage {

	WebDriver driver;
	String baseUrl = "https://translate.google.com";

	public TranslateActions(WebDriver driver) {
		super(driver, "TranslateActions");
		this.driver = driver;
	}

	public String launchBaseURL(String baseUrl) {
		driver.get(baseUrl);
		return driver.getTitle();
	}

	public Boolean inputEnglishWord(String keyword) {
		driver.findElement(By.xpath("//*[@id='source']")).sendKeys(keyword);
		return true;
	}

}
