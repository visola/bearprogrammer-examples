package com.bearprogrammer.blog.sample.integrationTest;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * To run the integration tests from the IDE you can use this class. It behaves
 * like a JUnit unit test but with the Cucumber runner it will set each scenario
 * as a unit test and run all the feature files using the glue code in the
 * directory and package specified.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features="src/integrationTest/features",glue="com.bearprogrammer.blog.sample.integrationTest.glue",strict=true)
public class RunIntegrationTests {
	
	@BeforeClass
    public static void initialize() throws Exception {
        System.setProperty("spring.profiles.active", "test");
        System.setProperty("webdriver", "Firefox");
        
        /* If you want to use Chrome as default webdriver, you need to set this variable here.
         * System.setProperty("webdriver", "Chrome");
         * System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver/executable"); 
         */
    }

}
