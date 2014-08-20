package com.bearprogrammer.blog.sample.integrationTest.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class MyExpectedConditions {
	
	public static ExpectedCondition<WebElement> forOneOfTheLocators(final By... locators) {
		return new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				for (By locator : locators) {
					WebElement el = maybeFindElement(locator, driver);
					if (el != null && el.isDisplayed()) {
						return el;
					}
				}
				return null;
			}
		};
	}
	
	static WebElement maybeFindElement(By locator, WebDriver driver) {
		try {
			return driver.findElement(locator);
		} catch (NoSuchElementException | TimeoutException ex) {}
		
		return null;
	}

}
