#Test to see that the associate can take a quiz

Feature:
	As an associate                                                                    #role
  I want to be able see my customized profiles                                       #action
  In order to take a quiz                                                            #business value

Background: 
	Given that the associate is able to view the quiz
	When that the associate clicks on the quiz 
  And the associate takes the quiz

Scenario: flag the questions
  And the associate clicks on the flag icon
  Then the associate is able to notice the colour of the flag change to red.
  
Scenario: unflag the questions
  And the associate clicks on the flag icon
  Then the associate is able to notice the colour of the flag has greyed out.
  

