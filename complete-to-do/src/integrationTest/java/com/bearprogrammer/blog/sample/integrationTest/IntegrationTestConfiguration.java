package com.bearprogrammer.blog.sample.integrationTest;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import com.bearprogrammer.blog.sample.integrationTest.selenium.LoginHelper;
import com.bearprogrammer.blog.sample.integrationTest.selenium.ScreenshotTakerEventListener;
import com.bearprogrammer.blog.sample.integrationTest.selenium.SpeedManagerEventLister;
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
    public WebDriver webDriver() throws Exception {
		logger.info("Initializing WebDriver");
		
		if (logger.isTraceEnabled()) {
			logger.trace("webdriver = '{}'", System.getProperty("webdriver"));
			logger.trace("seleniumInterval = '{}'", System.getProperty("seleniumInterval"));
			logger.trace("seleniumScreenshots = '{}'", System.getProperty("seleniumScreenshots"));
		}
		
		String driverName = "HtmlUnit"; // Defaults to HtmlUnit
		if (System.getProperty("webdriver") != null) {
			driverName = System.getProperty("webdriver");
		}
		
		logger.debug("Using {} web driver", driverName);
		
		/* This won't work with IE because package is not consistent with driver name.
		 * Chrome driver needs some setup: https://sites.google.com/a/chromium.org/chromedriver/getting-started
		 */
		Class<?> driverClass = Class.forName(String.format("org.openqa.selenium.%s.%sDriver",driverName.toLowerCase(), driverName));
		final WebDriver driver = (WebDriver) driverClass.newInstance();
		
		EventFiringWebDriver eventFiringDriver = new EventFiringWebDriver(driver);
		eventFiringDriver.register(new SpeedManagerEventLister());
		
		logger.debug("Using web driver: {}", driverClass.getName());
		if (driver instanceof TakesScreenshot && Boolean.TRUE.toString().equalsIgnoreCase(System.getProperty("seleniumScreenshots"))) {
			eventFiringDriver.register(new ScreenshotTakerEventListener());
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
        
        return eventFiringDriver;
    }
	
}