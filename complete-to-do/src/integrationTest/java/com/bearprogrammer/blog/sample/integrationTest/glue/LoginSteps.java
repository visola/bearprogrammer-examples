package com.bearprogrammer.blog.sample.integrationTest.glue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.bearprogrammer.blog.sample.integrationTest.IntegrationTestConfiguration;
import com.bearprogrammer.blog.sample.integrationTest.selenium.MyExpectedConditions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(classes=IntegrationTestConfiguration.class)
public class LoginSteps {
	
	@Autowired
	WebDriver driver;

	@Given("^I am at the login page$")
	public void goToLoginPage() {
		driver.navigate().to("http://localhost:8080/todo");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
	}

	@When("^I login with user '(.*)' and password '(.*)'$")
	public void loginAs(String user, String password) {
		driver.findElement(By.name("username")).sendKeys(user);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.cssSelector("input[type=submit]")).click();
		
		/* 
		 * For some tests, we may login with wrong user or password.
		 * This will wait for any one of the locators to be visible 
		 */
		new WebDriverWait(driver, 10)
			.until(MyExpectedConditions.forOneOfTheLocators(By.name("username"), By.xpath("//h2[contains(text(),\"To Do List\")]")));
	}

	@Then("^I should see the home page$")
	public void seeHomePage() {
		driver.findElement(By.xpath("//h2[contains(text(),\"To Do List\")]"));
	}
	
	@Then("^I can logout$")
	public void logout() {
		driver.findElement(By.cssSelector("input[type=submit][value=Logout]")).click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
	}

}
