package com.gusb.testweb;

import static org.junit.Assert.*;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

import com.project.pom.CartPage;
import com.project.pom.LogInPage;
import com.project.pom.ProductsPage;
import com.project.pom.WebDriverHelper;

public class SauceDemo_AddToCart_Test {
	static final Duration loadingWaitTime = Duration.ofSeconds(10);
	
	private static WebDriver driver;
	static CartPage cartPage;
	static ProductsPage productsPage;
	static LogInPage logInPage;
	
	@BeforeAll
	public static void setUp() throws Exception {
		driver = WebDriverHelper.chromeDriverConnection();
		cartPage = new CartPage(driver);
		productsPage = new ProductsPage(driver);
		logInPage = new LogInPage(driver);
	}

	@AfterAll
	public static void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testAddToCart() {
		logInPage.visitUrl(LogInPage.logInUrl);
		logInPage.implicitlyWait(loadingWaitTime);
		logInPage.logIn();
		logInPage.implicitlyWait(loadingWaitTime);
		
		assertTrue(productsPage.isProductsPageDisplayed());
		
		productsPage.addProductToCart(ProductsPage.onesieProductName);
		productsPage.goToCart();
		productsPage.implicitlyWait(loadingWaitTime);
		
		assertTrue(cartPage.isCartPageDisplayed());
		assertTrue(cartPage.isProductInCart(ProductsPage.onesieProductName));
	}
}
