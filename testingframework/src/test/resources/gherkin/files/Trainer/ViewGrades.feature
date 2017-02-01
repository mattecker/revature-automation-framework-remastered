#after logging in as a Trainer I should be able to view the grades of everyone in his batch
Feature: 
	As a Trainer
	I want to view the Grades of my associates
	In order to see where they need help
Scenario: Trainer wants to view the grades of current batch
Given I am logged in as a Trainer
And I choose the current batch
When I click  the view grades link
Then I should be able to see the grades of all my current associates

Scenario: Trainer wants to view the grades of a previous batch
Given I am logged in as a Trainer
And I choose a previous batch
When I click the view grades link
Then i should be able to see the grades of all the associates in the choosen previus batch 
