Feature:Test the square root of a number

@test
Scenario Outline: To test the square root of a number
Given I am in the site to calculate the square root of a number
When I enter an incorrect value "<value>" in the number field and calculate the square root
Then I get an error Value "<errorValue>"

Scenarios:
|value| errorValue |
| @@  | NaN          |


