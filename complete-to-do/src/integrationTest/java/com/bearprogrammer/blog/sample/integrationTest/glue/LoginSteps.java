package com.bearprogrammer.blog.sample.integrationTest.glue;

import org.springframework.test.context.ContextConfiguration;

import com.bearprogrammer.blog.sample.integrationTest.IntegrationTestConfiguration;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(classes=IntegrationTestConfiguration.class)
public class LoginSteps {

	@Given("^I am at the login page$")
	public void i_am_at_the_login_page() throws Throwable {
	}

	@When("^I login as a user$")
	public void i_login_as_a_user() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
//		throw new PendingException();
	}

	@Then("^I should see the home page$")
	public void i_should_see_the_home_page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
//		throw new PendingException();
	}

}
