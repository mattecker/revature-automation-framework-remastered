#After viewing the index page, the user should be able to either view/click signup button or login button

Feature:As a user													    																#role
	I want to be able to view the index page					    								#action
	In order to login																											#business logic

Scenario:The user is able to login	
	Given the index page is open
	When the user clicks on the login link
	And  the user accurately inputs the username
	And the user accurately inputs the password
	And   the user clicks the login button
	Then the user is able to login
	

Scenario: The user is not able to login because of incorrect username or/and password
	Given the index page is open
	When the user clicks on the login link
	And  the user inputs the invalid username
	And the user inputs the invalid password
	And   the user clicks the login button
	Then the user is redirected to the index page
	
