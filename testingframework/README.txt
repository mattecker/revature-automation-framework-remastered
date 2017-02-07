Revature Automation Framework

This application allows the user to automate testing on any website. This is
accomplished through a combination of keyword and data driven testing. The
user must supply an excel file containing two specifically named sheets:

Keywords :
	This spreadsheet will contain testing sections which contain individual
	steps in the section to be run by the program. The first row contains a
	header for each column.
	These five cells read:
		Test Case
		Keyword
		Object
		Object Type
		Value
	The program skips the first row, so it is important NOT to exclude this 
	first row. Each testing section begins with a section title, which is 
	placed in the first column. Each column after the section title should 
	be left blank. Following the section title, each row until the next section 
	title will contain steps to be run by the program. The first column is 
	not used in test steps. The second column must contain a keyword, or the 
	type of operation to be performed.
	These operations are:
		Click
		Select Drop-down
		Deselect Drop-down
		Deselect All
		Select Radio Button
		Select Checkbox
		Deselect Checkbox
		Input Text
		Go To URL
		Check Title
		Check URL
		Get Text
	Depending on which operation is to be performed, the program will use
	some or all of the next three columns. The third column contains an
	identifying attribute of a web element that the operation will be 
	performed on. The fourth column contains a value that tells the program
	what attribute of the web element is contained in column three. For
	example, if a button has a name attribute of 'btn', you can put 'btn'
	in column three, and put 'name' in column four. If column three has
	a value, column four must also contain a value. Finally, column five
	contains a variable that is contained within the Data Excel sheet.
	These values can serve many purposes, such as supplying input text.
	Here is a short keyword sheet example:
		|------------|-----------|-----------|-------------|------------------|
		|ScenarioName|  Keyword  |  Object   | Object Type |      Value       |
		|------------|-----------|-----------|-------------|------------------|
		|  Login     |           |           |             |                  |
		|------------|-----------|-----------|-------------|------------------|
		|            | Go To URL |           |             |     UrlColumn    |
		|------------|-----------|-----------|-------------|------------------|
		|            | InputText |  username |    name     |  UserNameColumn  |
		|------------|-----------|-----------|-------------|------------------|
		|            | InputText |  password |    name     |  passwordColumn  |
		|------------|-----------|-----------|-------------|------------------|
		|            |   Click   |   login   |    name     |                  |
		|------------|-----------|-----------|-------------|------------------|
Data :
	This sheet contains all of the variables used in the fifth column of the
	keyword sheet, or the value column. List the variables as they appear in
	the value column in the first row of the data sheet. Then, for each set
	of data the user wishes to test, they will fill the rows beneath the first
	row.
	Here is a short data sheet example:
		|-----------|----------------|----------------|
		| UrlColumn | UserNameColumn | passwordColumn |
		|-----------|----------------|----------------|
		|  Value 1  |    Value 2     |    Value 3     |
		|-----------|----------------|----------------|
		|  Value 4  |    Value 5     |    Value 6     |
		|-----------|----------------|----------------|
When the user starts the application, a window containing the graphical user
interface appears. The user will upload their excel sheet and select browsers
they wish to test before clicking run. The program will then perform each test
within the keywords sheet once for each row of data within the data sheet and
output the results.