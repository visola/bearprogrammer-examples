package com.bearprogrammer.blog.sample.integrationTest.glue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.bearprogrammer.blog.sample.integrationTest.IntegrationTestConfiguration;
import com.bearprogrammer.blog.sample.integrationTest.selenium.LoginHelper;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(classes={IntegrationTestConfiguration.class})
public class LoginSteps {
	
	@Autowired
	WebDriver driver;
	
	@Autowired
	LoginHelper loginHelper;
	
	@Given("^I am logged in with user '(.*)' and password '(.*)'$")
	public void loggedInAs(String user, String password) {
		loginHelper.goToLoginPage();
		loginHelper.loginWithUserAndPassword(user, password);
	}

	@Given("^I am at the login page$")
	public void goToLoginPage() {
		loginHelper.goToLoginPage();
	}

	@When("^I login with user '(.*)' and password '(.*)'$")
	public void loginAs(String user, String password) {
		loginHelper.loginWithUserAndPassword(user, password);
	}

	@Then("^I should see the home page$")
	public void seeHomePage() {
		driver.findElement(By.xpath("//h2[text() = 'To Do List']"));
	}
	
	@Then("^I can logout$")
	public void logout() {
		loginHelper.logout();
	}

}
