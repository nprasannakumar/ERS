Feature: To automate a registration form

	Scenario Outline: Verify the fields in the form 
	Given I open link "https://docs.google.com/forms/d/1HUzLL8WAFjDJOFN650rtB15JJGBbfZwQCs-OfjhpZew/viewform"
	When I fill in the first name "<firstName>"
	Then the form should be submitted successfully and no error message should be seen
	Then I close the browser
	
	Examples:
	| firstName |
	|  nayana   |
	|           |
	| Nayana@@@@,... |

	Scenario Outline: To ensure an error message is shown if the first name is empty
	Given I open link "https://docs.google.com/forms/d/1HUzLL8WAFjDJOFN650rtB15JJGBbfZwQCs-OfjhpZew/viewform"
	When I fill in the first name "<firstName>"
	Then an error message should be shown if the first name is empty
	Then I close the browser
	
	Examples:
	| firstName |
	|           |
	
	
	Scenario Outline: To ensure an error message is shown if the first name is empty
	Given I open link "https://docs.google.com/forms/d/1HUzLL8WAFjDJOFN650rtB15JJGBbfZwQCs-OfjhpZew/viewform"
	When I fill in the first name "<firstName>"
	Then an error message should be shown if the first name is invalid
	Then I close the browser
	
	Examples:
	| firstName      |
	| Nayana@@@@,... |
	
	Scenario: To ensure an error message is shown if a very long string is given
	Given I open link "https://docs.google.com/forms/d/1HUzLL8WAFjDJOFN650rtB15JJGBbfZwQCs-OfjhpZew/viewform"
	When I fill in a very long first name
	Then an error message should be shown if the first name is invalid
	Then I close the browser
	
	
	