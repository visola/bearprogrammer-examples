package com.bearprogrammer.blog.sample.integrationTest;

import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("com.bearprogrammer.blog.sample.test")
@Configuration
public class IntegrationTestConfiguration {
	
	@Bean
    public WebDriver getWebDriver() throws Exception {
		String driverName = "HtmlUnit"; // Defaults to HtmlUnit
		if (System.getProperty("webdriver") != null) {
			driverName = System.getProperty("webdriver");
		}
		
		// Chrome driver needs some setup: https://sites.google.com/a/chromium.org/chromedriver/getting-started
		final WebDriver driver = (WebDriver) Class.forName(String.format("org.openqa.selenium.%s.%sDriver",driverName.toLowerCase(), driverName)).newInstance();
        
        // Add shutdown hook to kill the browser when JVM dies
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run () {
                try {
                    driver.quit();
                } catch (Exception e) {
                    // Quietly die
                }
            }
        });
        
        return driver;
    }

}