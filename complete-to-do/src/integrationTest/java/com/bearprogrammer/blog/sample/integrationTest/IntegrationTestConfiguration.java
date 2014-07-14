package com.bearprogrammer.blog.sample.integrationTest;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@ComponentScan({"com.bearprogrammer.blog.sample.test","com.bearprogrammer.blog.sample.integrationTest"})
@Configuration
@Profile("test")
public class IntegrationTestConfiguration {
	
	Logger logger = LoggerFactory.getLogger(IntegrationTestConfiguration.class);
	
	public IntegrationTestConfiguration() {
		logger.debug("Initializing integration test configuration");
	}
	
	@Bean
    public WebDriver webDriver() throws Exception {
		logger.info("Initializing WebDriver");
		
		String driverName = "HtmlUnit"; // Defaults to HtmlUnit
		if (System.getProperty("webdriver") != null) {
			driverName = System.getProperty("webdriver");
		}
		
		logger.debug("Using {} web driver", driverName);
		
		/* This won't work with IE because package is not consistent with driver name.
		 * Chrome driver needs some setup: https://sites.google.com/a/chromium.org/chromedriver/getting-started
		 */
		final WebDriver driver = (WebDriver) Class.forName(String.format("org.openqa.selenium.%s.%sDriver",driverName.toLowerCase(), driverName)).newInstance();
        
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

}