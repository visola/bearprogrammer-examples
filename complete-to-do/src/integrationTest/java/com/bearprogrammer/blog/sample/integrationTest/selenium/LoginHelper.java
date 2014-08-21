package com.bearprogrammer.blog.sample.integrationTest.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginHelper {
	
	public static final String DEFAULT_PASSWORD = "password";
	
	WebDriver driver;
	
	public LoginHelper (WebDriver driver) {
		this.driver = driver;
	}
	
	public void goToLoginPage() {
		driver.navigate().to("http://localhost:8080/todo");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
	}
	
	public void loginWithUserAndDefaultPassword(String user) {
		loginWithUserAndPassword(user, DEFAULT_PASSWORD);
	}
	
	public void loginWithUserAndPassword(String user, String password) {
		driver.findElement(By.name("username")).sendKeys(user);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.cssSelector("input[type=submit]")).click();
		
		/* 
		 * For some tests, we may login with wrong user or password.
		 * This will wait for any one of the locators to be visible 
		 */
		new WebDriverWait(driver, 10)
			.until(MyExpectedConditions.forOneOfTheLocators(By.name("username"), By.xpath("//h2[contains(text(),'To Do List')]")));
	}
	
	public void logout() {
		driver.findElement(By.cssSelector("input[type=submit][value=Logout]")).click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
	}

}
