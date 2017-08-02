package com.qait.demo.tests;

import java.lang.reflect.Method;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qait.automation.TestSessionInitiator;
import junit.framework.Assert;

public class GoogleTranslatorTest {
	TestSessionInitiator test;
	String baseUrl;

	@BeforeClass
	public void Start_Test_Session() {
		test = new TestSessionInitiator(this.getClass().getSimpleName());

	}

	@BeforeMethod
	public void handleTestMethodName(Method method) {
		test.stepStartMessage(method.getName());
	}

	@Test
	public void isTranslatingFrenchThroughUrl() throws InterruptedException {
		Assert.assertTrue(test.databaseActions.launchBaseURL("https://translate.google.com").equals("Google Translate"));
		Assert.assertEquals(test.databaseActions.translateEnglishWordtoFrench("Hello"), "Bonjou");
	}

	@Test
	public void isTranslatingLatinThroughUrl() throws InterruptedException {
		Assert.assertTrue(test.databaseActions.launchBaseURL("https://translate.google.com").equals("Google Translate"));
		Assert.assertEquals(test.databaseActions.translateEnglishWordtoLatin("Hello"), "salve");
	}

	@Test
	public void isTranslatingIrishThroughUrl() throws InterruptedException {
		Assert.assertTrue(test.databaseActions.launchBaseURL("https://translate.google.com").equals("Google Translate"));
		Assert.assertEquals(test.databaseActions.translateEnglishWordtoIrish("Hello"), "Dia duit");
	}

	

	@AfterClass
	public void close_Test_Session() {
		test.closeBrowserSession();
	}
}
