Feature: Positive scenarios


Scenario Outline: Test all fields being positive
Given I am in the form page
When I enter valid value to fields suffix "<suffix>", firstName "<firstName>", lastName "<lastName>", phoneNumber "<phoneNumber>", emailId "<emailId>", date "<date>", gender "<gender>" and submit the form 
Then the form is submitted successfully and I get the success message "<successMessage>"

Scenarios:
|suffix| firstName | lastName | phoneNumber | emailId              | date     | gender | successMessage                  |
|  Ms  | Nayana    | Kumar    | 201-805-6666  | nayana23@ccs.neu.edu |12/05/1990| Female | Your response has been recorded.|
#|  Ms  | Nayana    | Kumar    | 2018056666  | nayana23@ccs.neu.edu |28/03/1990| Female | Your response has been recorded.|
#|  Ms  | Nayana    | Kumar    | (201)805-6666  | nayana23@ccs.neu.edu |28/03/1990| Female | Your response has been recorded.|

# failure test case


Scenario Outline: testing two related fields
Given I am in the form page
When I enter related values to fields suffix "<suffix>" and gender "<gender>" and valid values to fields firstName "<firstName>", lastName "<lastName>", phoneNo "<phoneNumber>", email "<emailId>", date "<date>" and submit the form
Then the form should not be submitted succesfully


Scenarios:
|suffix|gender| firstName | lastName | phoneNumber | emailId              | date     |
|  Mr  | Female| Nayana    | Kumar    | 201-805-6666  | nayana23@ccs.neu.edu |19/03/1990| 