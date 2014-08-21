package com.bearprogrammer.blog.sample.integrationTest.glue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;

import com.bearprogrammer.blog.sample.integrationTest.IntegrationTestConfiguration;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

@ContextConfiguration(classes = { IntegrationTestConfiguration.class })
public class ScenarioMarkerHook {
	
	public static Scenario scenario; 

	Logger logger = LoggerFactory.getLogger(ScenarioMarkerHook.class);
	Long start;

	public Scenario getScenario() {
		return scenario;
	}

	@After(order = 0)
	public void scenarioFinished(Scenario scenario) {
		logger.debug("Scenario finished in {} ms: '{}'", System.currentTimeMillis() - start, scenario.getName());
	}

	@Before(order = 0)
	public void scenarioStarted(Scenario scenario) {
		start = System.currentTimeMillis();
		ScenarioMarkerHook.scenario = scenario;
		logger.debug("Scenario started: '{}'", scenario.getName());
	}

}