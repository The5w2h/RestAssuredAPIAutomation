Feature: Application Login

Scenario: Home page default login
Given User is on Netbanking login page
When User login to the app with "john1" and "doe1"
Then Home page is opened
And Cards are displayed is "true"

Scenario: Home page default login
Given User is on Netbanking login page
When User login to the app with "john2" and "doe2"
Then Home page is opened
And Cards are displayed is "false"