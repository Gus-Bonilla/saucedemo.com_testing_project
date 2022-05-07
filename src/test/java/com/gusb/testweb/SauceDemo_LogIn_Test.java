package com.gusb.testweb;

import static org.junit.Assert.*;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

import com.project.pom.LogInPage;
import com.project.pom.ProductsPage;
import com.project.pom.WebDriverHelper;

public class SauceDemo_LogIn_Test {
	static final Duration loadingWaitTime = Duration.ofSeconds(10);
	
	private static WebDriver driver;
	static LogInPage logInPage;
	static ProductsPage productsPage;
	
	@BeforeAll
	public static void setUp() throws Exception {
		driver = WebDriverHelper.chromeDriverConnection();
		logInPage = new LogInPage(driver);
		productsPage = new ProductsPage(driver);
		//logInPage.visitUrl(baseUrl);
	}

	@AfterAll
	public static void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testValidLogIn() {
		logInPage.visitUrl(LogInPage.logInUrl);
		logInPage.implicitlyWait(loadingWaitTime);
		logInPage.logIn();
		logInPage.implicitlyWait(loadingWaitTime);
		assertTrue(productsPage.isProductsPageDisplayed());
	}
}
