package com.bearprogrammer.blog.sample.integrationTest.glue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;

import com.bearprogrammer.blog.sample.integrationTest.IntegrationTestConfiguration;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

@ContextConfiguration(classes={IntegrationTestConfiguration.class})
public class ScenarioMarkerHook {
	
	Logger logger = LoggerFactory.getLogger(ScenarioMarkerHook.class);
	
	@Before(order=Integer.MAX_VALUE)
	public void scenarioStarted(Scenario scenario) {
		logger.debug("Scenario started: '{}'", scenario.getName());
	}
	
	@After(order=Integer.MAX_VALUE)
	public void scenarioFinished(Scenario scenario) {
		logger.debug("Scenario finished: '{}'", scenario.getName());
	}

}
