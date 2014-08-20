Feature: Login Page

	As a user I want to be able to login and out of the system.
	
	Background:
		Given I am at the login page
		
	Scenario: Login as a user
		When I login with user 'janedoe' and password 'password'
		Then I should see the home page
		And I can logout
		
	Scenario: Login with no user
		When I login with user '' and password 'password'
		Then I should see the message 'Bad credentials'
		
	Scenario: Login with no password
		When I login with user 'janedoe' and password ''
		Then I should see the message 'Bad credentials'
		
	Scenario: Login with wrong user
		When I login with user 'wrong' and password 'password'
		Then I should see the message 'Bad credentials'
		
	Scenario: Login with wrong password
		When I login with user 'janedoe' and password 'wrong'
		Then I should see the message 'Bad credentials'
