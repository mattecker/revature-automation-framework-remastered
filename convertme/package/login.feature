Feature: LogIn Test
 
Scenario: LogIn
Given: The browser is open and the URL is UrlProperty
When: The user inputs USERNAME by name as yuvi1	
And: The user inputs PASSWORD by name as yuvi1
And: The user clicks login by name
Then: the user Should be Logged  and at the loggedinTitle webpage

Scenario: FindFlights
Given: The user is logged in
When: The user selects a radiobutton tripType by name as tripRadio
And: The user selects from dropdown passCount by name as passengers
And: The user selects from dropdown fromPort by name as fromPort
And: The user selects from dropdown fromMonth by name as fromDate
And: The user selects from dropdown fromDay by name as fromDay
And: The user selects from dropdown toPort by name as toPort
And: The user selects from dropdown toMonth by name as toDate
And: The user selects from dropdown toDay by name as toDay
And: The user selects radiobutton servClass by name as class
And: The user selects from dropdown airline by name as planeType
And: The user clicks findFlights by findFlightsSubmit
Then: The user should be at findFlightTitle webpage

Scenario: SelectFlights
Given: the user searched for flights
When: the user selects from radiobutton outFlight by name as outlflights
And: the user selects from radiobutton inFlight by name as inflights
And: The user clicks reserveFlights by name
Then: the user should be at the submitwebpage

Scenario: Book
Given: the user has selected flights
When: the user inputs passFirst0 by name as firstName
And: the user inputs passLast0 by name as lastname
And: the user selects from dropdown pss.0.meal by name as mealPreference
And: the user selects from dropdown creditCard by name as creditCardType
And: the user inputs creditnumber by name as creditCardNumber
And: the user selects from dropdown cc_exp_dt_mn by name as monthExp
And: the user selects from dropdown cc_exp_dt_mn by name as yearExp
And: the user inputs cc_first_name by name as ccfirstName
And: the user inputs cc_last_name by name as cclastName
And: the user inputs billAddres1 by name as adressbill
And: the user inputs billcity by name as city
And: the user inputs billState by name as state
And: the user inputs billZip by name as zip
And: the user inputs billCountry by name as country
Then: the user shoudl be at the confirm webpage

Scenario: Logout
Given: the user wants to sign out 
When: the user  clicks logout by name
Then: the user should be  at the Loggoff webpage



