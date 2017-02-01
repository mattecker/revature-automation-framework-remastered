#To verify the QC can generate the reports

Feature:
	As a QC                            							  #role
  I want to be able write the reports							  #action
  In order to submit them to the trainer				  	#business value

Scenario: Write reports about the performance of a batch
	Given that the QC is able to view batch
	When the QC clicks on write review
	And the QC inputs the date
	And the QC writes the comments
	And the QC submits the report
  Then the QC is able to forward the report for the trainers to see
