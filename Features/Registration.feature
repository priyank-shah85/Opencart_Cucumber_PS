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
	Scenario: Verify red asterisk sign for Mandatory fields.
		Given User launch browser
		And opens URL "http://localhost/opencart/upload/index.php"
		When User navigates to MyAccount menu
		And click on Register
		Then Page displays red asterisk sign for mandatory fields
		
@negative @regression @Major
	Scenario: Verify validation messages for Mandatory fields.
		Given User launch browser
		And opens URL "http://localhost/opencart/upload/index.php"
		When User navigates to MyAccount menu
		And click on Register
		And click on Continue button
		Then Page displays the validation messages for mandatory fields