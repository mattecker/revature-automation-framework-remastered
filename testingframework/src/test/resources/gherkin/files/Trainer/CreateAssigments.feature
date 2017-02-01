#after logging in as a Trainer i should be able to add an assignmentfor the batch
Feature: 
	As a Trainer
	I want to add an assingment
	In order to tests my associates knowledge

Scenario: Trainer wants to add an Assingement to the class
Given I am logged in as a Trainer
When I click  the new assignment link
Then I should be able to assign a project or test 

