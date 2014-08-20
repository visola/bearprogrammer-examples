package com.bearprogrammer.blog.sample.integrationTest;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/integrationTest/features",glue="com.bearprogrammer.blog.sample.integrationTest.glue",strict=true)
public class RunIntegrationTests {

}
