Feature: To automate kayak 

@test
Scenario Outline: To verify that the lowest flight fares are given by Kayak
Given I am in the site to check flight fares
When I enter the source "<source>" and destination "<destination>" cities and select the start date "<startDate>" and end date "<endDate>"
Then I verify the search results
 

Scenarios:
|source | destination     | startDate        | endDate |
|Boston, MA | Austin, TX|   April 17       | April 21|