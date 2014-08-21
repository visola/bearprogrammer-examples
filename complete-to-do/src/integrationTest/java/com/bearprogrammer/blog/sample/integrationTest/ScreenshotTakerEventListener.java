package com.bearprogrammer.blog.sample.integrationTest;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bearprogrammer.blog.sample.integrationTest.glue.ScenarioMarkerHook;

public class ScreenshotTakerEventListener extends AbstractWebDriverEventListener{
	
	Logger logger = LoggerFactory.getLogger(ScreenshotTakerEventListener.class);
	
	String timestamp = String.format("%1$tY%1$tm%1$te%1$tH%1$tM%1$tS%1$tL",Calendar.getInstance());
	
	Integer counter = 1;
	
	String lastScenario;
	
	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		takeScreenShot(driver);
	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		takeScreenShot(driver);
	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		takeScreenShot(driver);
	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		takeScreenShot(driver);
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		takeScreenShot(driver);
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		takeScreenShot(driver);
	}

	@Override
	public void afterScript(String script, WebDriver driver) {
		takeScreenShot(driver);
	}

	private void takeScreenShot(WebDriver driver) {
		if (!Boolean.TRUE.toString().equalsIgnoreCase(System.getProperty("seleniumScreenshots"))) return; 
			
		File screenshot = ( (TakesScreenshot) driver ).getScreenshotAs(OutputType.FILE);
		
		String scenario = ScenarioMarkerHook.scenario == null ? "no-scenario" : ScenarioMarkerHook.scenario.getName();
		if (!scenario.equals(lastScenario)) {
			lastScenario = scenario;
			counter = 1;
		}
		
		File destScreenshot = new File(String.format("build/screenshots/%s/%s",timestamp, scenario), String.format("screenshot-%04d.png", counter));
		counter++;
		
		try {
			destScreenshot.getParentFile().mkdirs();
			FileUtils.copyFile(screenshot, destScreenshot);
		} catch (IOException ioe) {
			logger.warn("Error while getting screenshot.",ioe);
		}
	}

}
