Feature:

	As a user I want to be able to login and logout of the system.
	
	Background:
		Given I am at the login page
		
	Scenario: Login as a user
		When I login as a user
		Then I should see the home page
