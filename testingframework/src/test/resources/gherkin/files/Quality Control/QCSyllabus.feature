#To verify the QC is able to view the syllabus

Feature:
	As an QC                                                                    #role
  I want to be able see the syllabus				                                       #action
  In order to perform quality checks							                                     #business value

Scenario: 
	Given that the QC is able to view the batch
	When the QC selects the batch
	And the QC clicks on the syllabus for the week
	And the QC submits it
  Then the QC is able to view the syllabus for the week
