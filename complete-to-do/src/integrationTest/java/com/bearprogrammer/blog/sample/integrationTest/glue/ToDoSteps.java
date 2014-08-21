package com.bearprogrammer.blog.sample.integrationTest.glue;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.bearprogrammer.blog.sample.integrationTest.IntegrationTestConfiguration;
import com.bearprogrammer.blog.sample.integrationTest.selenium.LoginHelper;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(classes={IntegrationTestConfiguration.class})
public class ToDoSteps {
	
	private static final String SOME_TO_DO = "Some To Do";
	
	@Autowired
	LoginHelper loginHelper;
	
	@Autowired
	WebDriver driver;
	
	@When("^I click create new to do item$")
	public void clickCreateToDo() {
		driver.findElement(By.linkText("Create")).click();
	}
	
	@When("^I give it a description and assign it to '(.*)'$")
	public void addDescriptionAndAssignTo(String user) {
		driver.findElement(By.id("action")).sendKeys(SOME_TO_DO);
		
		Select select = new Select(driver.findElement(By.id("assignedTo.username")));
		for (WebElement option : select.getOptions()) {
			if (option.getText().equals(user)) {
				option.click();
			}
		}
		
		driver.findElement(By.xpath("//input[@value='Save']")).click();
	}
	
	@Then("^I should see the information in the list of to dos$")
	public void shouldSeeSomeToDo() {
		driver.findElement(By.linkText(SOME_TO_DO));
	}
	
	@Then("^I should see all tasks$")
	public void shouldSeeAllTasks() {
		List<WebElement> elements = driver.findElements(By.xpath("//table/tbody/tr"));
		Assert.assertEquals("Should find 4 To Dos", 4, elements.size());
	}
	
	@Then("^'(.*)' should see the to do item in his/her list$")
	public void userSeeToInList(String username) {
		loginHelper.logout();
		loginHelper.loginWithUserAndDefaultPassword(username);
		driver.findElement(By.linkText(SOME_TO_DO));
	}

}