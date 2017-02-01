#This verifies the index page exists and the user is able to view this page by entering the correct URL

Feature:
	As a user													    																	    #role
	I want to be able to view the index page												    		    #action
	In order to login or signup										 															#business value
	
Scenario:
	Given the web browser is open
	When the user inputs the URL
	And the user presses enter
	Then the user is able to view the index page
	
