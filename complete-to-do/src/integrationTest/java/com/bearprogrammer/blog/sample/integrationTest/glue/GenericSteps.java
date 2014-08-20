package com.bearprogrammer.blog.sample.integrationTest.glue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.bearprogrammer.blog.sample.integrationTest.IntegrationTestConfiguration;

import cucumber.api.java.en.Then;

@ContextConfiguration(classes={IntegrationTestConfiguration.class})
public class GenericSteps {
	
	Logger logger = LoggerFactory.getLogger(GenericSteps.class);
	
	@Autowired
	WebDriver driver;

	@Then("^I should see the message '(.*)'$")
	public void loginAs(final String message) {
		// Wait for 1 second for the page to contain the message
		new WebDriverWait(driver, 1).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return driver.getPageSource().contains(message);
			}
		});
	}

}
