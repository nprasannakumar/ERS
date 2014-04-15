Feature: Validation

@test
Scenario Outline: Test one field at a time for invalid patterns passed
Given I am in the form page
#When I enter incorrect value only to field "<fieldName>" and value "<value>"
#Then I get the error "<errorMessage>"

Scenarios:
|fieldName| value | errorMessage|
|firstName| @@nekfe |Must match pattern|
|lastName | @@nekfe |Must match pattern|
#|phoneNumber| gtlbmtm|Must match pattern|
#|email    | jvbjbvjerb| Must match pattern|
#|email    | 2222      | Must match pattern|
#|email    | rone@     | Must match pattern|
#|email    |  xxxx@yyyy.zzz! | Must match pattern|


Scenario Outline: Test one valid field at a time for length greater than the required 
Given I am in the form page
When I enter strings in field "<fieldName>" greater than the allowed length <length>
Then I get the error "<errorMessage>"

Scenarios:
|fieldName|length| errorMessage|
|firstName|20| Must match pattern|
|lastName|20| Must match pattern|
|phoneNumber|11| Must match pattern|
|email|60| Must match pattern|


Scenario Outline: Test one field at a time for empty 
Given I am in the form page
When I leave the "<missing_field>" as empty and enter suffix as "<suffix>" firstName as "<firstName>", lastName as "<lastName>", phoneNumber as "<phoneNumber>", emailId as "<email>", dob as "<dob>" and gender as "<gender>"
Then I get the required message error as "<errorMessage>"

Scenarios:
|missing_field| suffix | firstName | lastName | phoneNumber | email             | dob       | gender | errorMessage                |
#| suffix      |        | Nayana    | Kumar    | 2018056666  | naina88@gmail.com | 28/03/1990| Female | This is a required question |
#| firstName   |  Ms    |           | Kumar    | 2018056666  | naina88@gmail.com | 28/03/1990| Female | This is a required question |
#| lastName    |  Ms    | Nayana    |          | 2018056666  | naina88@gmail.com | 28/03/1990| Female | This is a required question |
#| phoneNumber  |  Ms    | Nayana    | Kumar    |             | naina88@gmail.com | 28/03/1990| Female | This is a required question |
#| email       |  Ms    | Nayana    |  Kumar    | 2018056666  |                    | 28/03/1990| Female | This is a required question |
| dob         |  Ms    | Nayana    |  Kumar    | 2018056666  |naina88@gmail.com   |           | Female | This is a required question |
#| gender      |   Ms    | Nayana    | Kumar    | 2018056666  | naina88@gmail.com | 28/03/1990|          | This is a required question |


