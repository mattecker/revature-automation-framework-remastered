#To verify the QC is able to view the Calender

Feature:
	As an QC                                                                    #role
  I want to be able see the QC					                                       #action
  In order to view and add events								                                     #business value

Scenario: 
	Given that the QC is able to view the calendar
	When the QC clicks on the new event button
	And the QC inputs the event title
	And the QC inputs the Description
	And the QC submits it
  Then the QC is able to view the events
