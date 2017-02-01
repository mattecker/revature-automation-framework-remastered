#After logging in as a trainer  i should be able to create a new batch

Feature: Add a new Batch
	As a Trainer 
	I want to add a new batch
	In order to add incoming associates to it


Scenario: Create new Batch
Given I am logged in as a trainer
When I click on a new batch link
Then I should be able to create a new batch
	

