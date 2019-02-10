Feature: Retrive the Instrument Traded volume within the given date and List of instrument not traded between the range

 
Scenario: Check that Traded volume between the dates are displayed
Given I launch Chrome browser and open trading application
When I enter todate and fromdate and click on Submit
Then I verify that the Instrument Traded volume within the given date and List of instrument not traded between the range are displayed




 
  