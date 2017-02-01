#after logging in  i Should be able to view the grades of all the associates 
Feature: View Grades for associates
	As a Quality Control user
	i want to  view the grafes of all associates
	to view the progress  of the associates
	
	Scenario: View grades for all Current Associates
	Given I am logged in as a management user
	When  I click the view all current associates grades 
	Then  I should be able view the grades for all the current associates
	
	Scenario: View grades for a specific batch
	Given I am logged in as a management user
	And 	I have selected a specific batch
	When  I click the view  associates grades 
	Then  I should be able view the grades for all the current associates in that batch
	
