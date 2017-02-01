#To verify the associate is able to view the Calender

Feature:
	As an associate                                                                    #role
  I want to be able see the calendar					                                       #action
  In order to view and add events								                                     #business value

Scenario: 
	Given that the associate is able to view the calendar
	When the associate clicks on the new event button
	And the associate inputs the event title
	And the associate inputs the Description
	And the associate submits it
  Then the associate is able to view the events
