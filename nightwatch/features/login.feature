# features/login.feature

Feature: Login

Scenario: Login to Flight

  Given I open Flight Finder
  Then the title is "Welcome: Mercury Tours"
  And the username form exists
  And the password form exists
  When I type "yuvi1" as username
  And I type "yuvi1" as password
  And I click login
  And I wait "1000"
  Then the title is "Find a Flight: Mercury Tours:"
