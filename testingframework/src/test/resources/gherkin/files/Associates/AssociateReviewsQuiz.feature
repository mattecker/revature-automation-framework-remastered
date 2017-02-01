#To verify the associate is able to review the quiz as well as view their grades

Feature:
	As an associate                                                                    #role
  I want to be able see my customized profiles                                       #action
  In order to review the quiz and view my grades                                     #business value

Scenario: 
	Given that the associate is able to view the past quizzes
	When that the associate clicks on the quiz to review
  Then the associate is able to view the grades and then review

Scenario: flag the questions
  And the associate clicks on the flag icon
  Then the associate is able to notice the colour of the flag change to red.
  
Scenario: unflag the questions
  And the associate clicks on the flag icon
  Then the associate is able to notice the colour of the flag has greyed out.
  

