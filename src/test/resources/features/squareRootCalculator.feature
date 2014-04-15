Feature:Test the square root of a number


Scenario Outline: To test the square root of a number for invalid values
Given I am in the site to calculate the square root of a number
When I enter an incorrect value "<value>" in the number field and calculate the square root
Then I get an error Value "<errorValue>"

Scenarios:
|value| errorValue |
| @@  |   Error!   |
| -10 |   Error!   |
| -20.855 | Error! |
| abcd  | Error!   |
|       | Error!   |

#check if anything else is needed : negative scenario


Scenario Outline: To test the square root of a number for valid values
Given I am in the site to calculate the square root of a number
When I enter a valid value "<value>" in the number field and calculate its square root
Then I get the square root of the number "<value>"

Scenarios:
|value|
|64.23|
| 4   |
|2147483647|
|0| 
#try for a no greater that largest 32 bit 
