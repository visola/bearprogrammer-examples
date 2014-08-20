package com.bearprogrammer.blog.sample.integrationTest.glue;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.bearprogrammer.blog.sample.integrationTest.IntegrationTestConfiguration;

import cucumber.api.java.After;

@ContextConfiguration(classes={IntegrationTestConfiguration.class})
public class SeleniumConfigurationHook {
	
	Logger logger = LoggerFactory.getLogger(SeleniumConfigurationHook.class);
	
	@Autowired
	WebDriver driver;
	
	@After
	public void cleanCookies() {
		driver.manage().deleteAllCookies();
		logger.debug("Cleaning cookies...");
	}

}
