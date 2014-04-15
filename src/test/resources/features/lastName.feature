Feature: To automate a registration form

	Scenario Outline: Verify the fields in the form 
	Given I open link "https://docs.google.com/forms/d/1HUzLL8WAFjDJOFN650rtB15JJGBbfZwQCs-OfjhpZew/viewform"
	When I fill in the first name "<firstName>", last name "<lastName>"
	Then the form should be submitted successfully and no error message should be seen
	
	Examples:
	| firstName | lastName |
	|  nayana   | kumar    |
	
	Scenario Outline: Verify the fields firstName and when last name is empty
	Given I open link "https://docs.google.com/forms/d/1HUzLL8WAFjDJOFN650rtB15JJGBbfZwQCs-OfjhpZew/viewform"
	When I fill in the first name "<firstName>", last name "<lastName>"
	Then an error message is shown if the last name is empty
	
	Examples:
	| firstName | lastName |
	| nayana    |          |
	
	Scenario Outline: Verify the fields firstName and when last name is invalid
	Given I open link "https://docs.google.com/forms/d/1HUzLL8WAFjDJOFN650rtB15JJGBbfZwQCs-OfjhpZew/viewform"
	When I fill in the first name "<firstName>", last name "<lastName>"
	Then an error message is shown if the last name is invalid
	
	Examples:
	| firstName | lastName 			  |
	| nayana    | kumar@@@...         |
	
	Scenario: Verify the firstName and when last name is too long
	Given I open link "https://docs.google.com/forms/d/1HUzLL8WAFjDJOFN650rtB15JJGBbfZwQCs-OfjhpZew/viewform"
	When i fill in a valid first name "<firstName>" and a very long last name
	Then an error message is shown if the last name is invalid
	
	
	Should I test for these scenarios below???
	
	Other scenario examples :
	
	| firstName |  lastName                       |
	|           |   kumar                         |
	| nayana@@@ |  kumar                          |
	| ncenfefgijgiorjgiri | kumar                 |
	|                     |                       |
	| nayana@@@@vvr       | kumar@@@@@            |
	| eknfkrnrkngkgnrengrg| klgnrklgnkrngkrngqrklm| 
	
	
	
	