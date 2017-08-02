package com.qait.demo.keywords;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.RedirectConfig.redirectConfig;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.base.CharMatcher;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.qait.automation.getpageobjects.GetPage;

public class ComputerDatabaseActions extends GetPage {
	private WebDriver driver;

	public ComputerDatabaseActions(WebDriver driver) {
		super(driver, "ComputerDatabaseActions");
		this.driver = driver;
	}

	public String launchBaseURL(String baseUrl) {
		driver.get(baseUrl);
		return driver.getTitle();
	}

	
	
	
	
	
	
	
	
	public String translateEnglishWordtoFrench(String keyword) throws InterruptedException {
		driver.findElement(By.xpath(".//*[@id='gt-sl-gms']")).click();
		driver.findElement(By.xpath("//*[@id=':p']")).click();
	
		driver.findElement(By.xpath("//*[@id='source']")).sendKeys(keyword);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='gt-tl-gms']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=':3v']")).click();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath("//*[@id='result_box']"));
		String mat=element.getText().toString();
	    return mat;
	}
	
	public String translateEnglishWordtoLatin(String keyword) throws InterruptedException {
		driver.findElement(By.xpath(".//*[@id='gt-sl-gms']")).click();
		driver.findElement(By.xpath("//*[@id=':p']")).click();
	
		driver.findElement(By.xpath("//*[@id='source']")).sendKeys(keyword);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='gt-tl-gms']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='gt-tl-gms-menu']/table/tbody/tr/td[4]/div/div[7]")).click();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath("//*[@id='result_box']"));
		String mat=element.getText().toString();
	    return mat;

	}
	
	public String translateEnglishWordtoIrish(String keyword) throws InterruptedException {
		driver.findElement(By.xpath(".//*[@id='gt-sl-gms']")).click();
		driver.findElement(By.xpath("//*[@id=':p']")).click();
	
		driver.findElement(By.xpath("//*[@id='source']")).sendKeys(keyword);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='gt-tl-gms']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='gt-tl-gms-menu']/table/tbody/tr/td[3]/div/div[12]")).click();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath("//*[@id='result_box']"));
		String mat=element.getText().toString();
	    return mat;
	}
	


	
	
	
	
	
	
	
	
	
	public int getTotalCountOfComputerThroughUI() {
		int number = Integer
				.parseInt(CharMatcher.digit().retainFrom(driver.findElement(By.cssSelector("#main>h1")).getText()));
		logMessage("[TEST PASSED]: Number Of Mahcines before addition:" + number);
		return number;
	}

	public boolean addComputerThroughAPI(String name) {
		RequestSpecification resSpec = given()
				.config(RestAssured.config().redirect(redirectConfig().followRedirects(false)));
		String finalURI = "http://computer-database.gatling.io/computers";
		HashMap<String, String> payload = new HashMap<>();
		payload.put("name", name);
		payload.put("introduced", "2017-05-03");
		payload.put("discontinued", "2017-07-03");
		payload.put("company", "2");
		Response response = resSpec.given().contentType(ContentType.JSON).with().body(payload).when().post(finalURI)
				.then().statusCode(303).extract().response();

		boolean b = response.getHeader("Set-Cookie").contains("success=Computer+" + name);
		logMessage("[API TEST PASSED]: Computer Created: " + name);
		return b;
	}

	public String validateComputerIsCreatedSuccessfully(String computerName) {
		element("search-field").clear();
		element("search-field").sendKeys(computerName);
		element("search-submit").click();
		return element("search-result").getText().trim();
	}

	public boolean addComputerfromFrontend(String name) {
		element("add-computer").click();
		wait.hardWait(1);
		element("computer-name").sendKeys(name);
		wait.hardWait(1);
		element("computer-introduced-date").sendKeys("2017-05-04");
		wait.hardWait(1);
		element("computer-discontinued").sendKeys("2017-05-30");
		wait.hardWait(1);
		selectProvidedTextFromDropDown(element("company-dropdown"), "Apple Inc.");
		element("save").click();
		return true;
	}

}
