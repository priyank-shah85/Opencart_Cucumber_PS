Feature: Account Registration.

@sanity @happypath @regression @Critical
	Scenario: Successfully register by providing only mandatory fields.
		Given User launch browser
		And opens URL "http://localhost/opencart/upload/index.php"
		When User navigates to MyAccount menu
		And click on Register
		And User enters First Name as "priyank" and Last Name as "shah1"
		And User enters E-mail as "priyankshah1@gmail.com"
		And User enters Password as "test@123"
		And User agree to the Privacy Policy
		And click on Continue button
		Then User navigates to Account Confirmation page
		
@regression @Minor
	Scenario: Verify fields available on page.
		Given User launch browser
		And opens URL "http://localhost/opencart/upload/index.php"
		When User navigates to MyAccount menu
		And click on Register
		Then Verify the fields available on page
		
@negative @regression @Major
	Scenario: Verify validation messages for Mandatory fields.
		Given User launch browser
		And opens URL "http://localhost/opencart/upload/index.php"
		When User navigates to MyAccount menu
		And click on Register
		And click on Continue button
		Then Page displays the validation messages for mandatory fields
		
@regression @Major @temp
	Scenario Outline: Check registration with Newletter as Yes & No respectively.
		Given User launch browser
		And opens URL "http://localhost/opencart/upload/index.php"
		When User navigates to MyAccount menu
		And click on Register
		And User enters First Name as "priyank" and Last Name as "shah1"
		And User enters Email as "<email>" and selects Newsletter as "<subscribe>"
		And User enters Password as "test@123"
		And User agree to the Privacy Policy
		And click on Continue button
		Then User navigates to Account Confirmation page
		
		Examples:
		|email									|subscribe|
		|priyankshah1@gmail.com	|Yes			|
		|priyankshah2@gmail.com	|No				|
		
		
		