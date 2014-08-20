package com.bearprogrammer.blog.sample.integrationTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class SpeedManagerEventLister extends AbstractWebDriverEventListener {

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		controlSpeed();
	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		controlSpeed();
	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		controlSpeed();
	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		controlSpeed();
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		controlSpeed();
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		controlSpeed();
	}

	@Override
	public void afterScript(String script, WebDriver driver) {
		controlSpeed();
	}

	private void controlSpeed() {
		String speed = System.getProperty("seleniumInterval");
		if (speed != null) {
			try {
				Thread.sleep(Integer.parseInt(speed));
			} catch (InterruptedException ie) {}
		}
	}

}
