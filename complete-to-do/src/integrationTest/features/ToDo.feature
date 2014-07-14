Feature:

	As a user I want to create a to do item.
	
	Scenario: I create a to do item for myself
		Given I am logged in with user 'janedoe' and password 'password'
		When I click create new to do item
		And I give it a description and assign it to 'janedoe' 
		Then I should see the information in the list of to dos
		
	Scenario: I create a to do item for someone else
		Given I am logged in with user 'janedoe' and password 'password'
		When I click create new to do item, give it a description and assign it to 'johndoe' 
		Then I should see the information in the list of to dos
		And 'johndoe' should see the to do item in his/her list
		
	Scenario: Administrator can see all to dos
		When I am logged in with user 'johndoe' and password 'password'
		Then I should see all tasks 
