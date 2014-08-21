package com.bearprogrammer.blog.sample.integrationTest;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import com.bearprogrammer.blog.sample.integrationTest.selenium.LoginHelper;
import com.bearprogrammer.blog.sample.test.DatabaseCleaner;

@Configuration
@Import(IntegrationTestDatabaseConfiguration.class)
@Profile("test")
public class IntegrationTestConfiguration {
	
	Logger logger = LoggerFactory.getLogger(IntegrationTestConfiguration.class);
	
	public IntegrationTestConfiguration() {
		logger.debug("Initializing integration test configuration");
	}
	
	@Bean
	public DatabaseCleaner databaseCleaner() {
		return new DatabaseCleaner();
	}
	
	@Bean
	public LoginHelper loginHelper(WebDriver driver) {
		return new LoginHelper(driver);
	}
	
	@Bean
    public WebDriver webDriver(List<WebDriverEventListener> webDriverEventListeners) throws Exception {
		logger.info("Initializing WebDriver");
		
		String driverName = "HtmlUnit"; // Defaults to HtmlUnit
		if (System.getProperty("webdriver") != null) {
			driverName = System.getProperty("webdriver");
		}
		
		logger.debug("Using {} web driver", driverName);
		
		/* This won't work with IE because package is not consistent with driver name.
		 * Chrome driver needs some setup: https://sites.google.com/a/chromium.org/chromedriver/getting-started
		 */
		final WebDriver driver = new EventFiringWebDriver((WebDriver) Class.forName(String.format("org.openqa.selenium.%s.%sDriver",driverName.toLowerCase(), driverName)).newInstance());
		
		EventFiringWebDriver eventFiringDriver = (EventFiringWebDriver) driver;
		for (WebDriverEventListener eventListener : webDriverEventListeners) {
			eventFiringDriver.register(eventListener);
		}
        
        // Add shutdown hook to kill the browser when JVM dies
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run () {
                try {
                	logger.debug("Exiting web driver");
                    driver.quit();
                } catch (Exception e) {
                    // Quietly die if any exception happens
                }
            }
        });
        
        return driver;
    }
	
	@Bean
	public WebDriverEventListener speedManagerEventListener() {
		return new SpeedManagerEventLister();
	}
	
	@Bean
	public WebDriverEventListener screenshotTakerEventListener() {
		return new ScreenshotTakerEventListener();
	}

}