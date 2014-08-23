## What's in this application

This is a complete but bery simple To Do application using Spring Web MVC, Spring Security and Spring Data.
The goal with this application was to demonstrate how a web application can be tested from different perspectives.
To reach this goal there are three levels of testing in this application:

1. **Unit test** - JUnit tests that don't access files nor database. Focused on testing specific units of code with mocking of parts that aren't being tested. Very good to test controller and service classes.
1. **Functional test** - JUnit tests that get a real Spring application context and dependency injection. It also has access to a database that gets rolled back after every test. Very good to test pieces of code that will interact with JPA and database to make sure that transactions are being set correctly and caching is affecting the behavior of the code correctly.
1. **Integration test** - [Cucumber JVM](https://github.com/cucumber/cucumber-jvm/) with Spring's dependency injection and [Selenium](http://docs.seleniumhq.org/) that need an application running. These tests are focused on the behavior of each feature and are written in human (non-programmer) readable format.

## Setup

To run this application and its tests you'll need to setup a database. 
The one that comes configured is MySQL but you can easily change to use any database that has a JDBC driver.
The files you'll need to change to make it work are:

* src/main/resources/database.properties
* src/functionalTest/resources/functionalTestDatabase.properties
* src/acceptanceTest/resources/acceptanceTestDatabase.properties

The only thing you will have to change is the username and password to access your MySQL server.

## Switching to a different database

You may have to change the migrates that create the tables and test data if you change to a different database since Hibernate expects different data type mappings for different dialects.
The files you'll need to be aware of are:

* src/main/resources/db/migration/V0001__Initial model.sql
* src/main/resources/db/migration/V0002__To Do model.sql
* src/acceptanceTest/resources/testdb/migration/V1000__Add users.sql
* src/acceptanceTest/resources/testdb/migration/V1001__Add Test To Dos.sql