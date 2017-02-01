#This verifies the user to input and sign up 

Feature:

	As a user                                                                                      #role
  I want to be able to fill the form                                                             #action
  In order to signup                                                                               #business value

Background:

	Given the index page is open
	When the user clicks on the signup link
	And the user inputs the FirstName
	And the user inputs the LastName
	And the user inputs the email
		

Scenario: Sign up Success
   And the user inputs the password
  And the user reinputs the password
  And the passwords match
  And the user presses the signup button
  Then the user is able to see the successful login message
  
	Scenario: user tries to sign up twice with the same email address
  But the user inputs a duplicate email
  And the user inputs the password
  And the user reinputs the password
  And the user presses the signup button
  Then the user should be informed that the email address already exists
  

  Scenario: Invalid password during signups
  But the user inputs the password that does not fit the criteria
  And the user presses the signup button
  Then the user should be informed to recreate a valid password

  Scenario: The database does not recognize the user
  And : the firstname, LastName and email of the user is verified by the database
  Then : the user should get an error message saying the system does not recognize them


  Scenario: The database does not recognize the user
  And : the firstname, LastName and email of the user is verified by the database
  Then : the user should get an error message saying the system does not recognize them
