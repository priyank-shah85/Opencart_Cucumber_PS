Feature: Account Registration.

@sanity @happypath @regression @Critical
	Scenario: Successfully register by providing only mandatory fields.
		Given User launch browser
		And opens URL "http://localhost/opencart/upload/index.php"
		When User navigates to MyAccount menu
		And click on Register
		And User enters First Name as "priyank" and Last Name as "shah22"
		And User enters E-mail as "priyank22@gmail.com"
		And User enters Password as "test@123"
		And User agree to the Privacy Policy
		And click on Continue button
		Then User navigates to Account Confirmation page
		And User navigates to MyAccount menu
		And click on Logout
		
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
		
@regression @Major
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
		And User navigates to MyAccount menu
		And click on Logout
		
		Examples:
		|email								|subscribe|
		|priyanks5@gmail.com	|Yes			|
		|priyanks6@gmail.com	|No				|
		
@sanity @regression @Major
	Scenario: Verify validation for duplicate email account.
		Given User launch browser
		And opens URL "http://localhost/opencart/upload/index.php"
		When User navigates to MyAccount menu
		And click on Register
		And User enters First Name as "priyank" and Last Name as "s"
		And User enters E-mail as "priyankshah@gmail.com"
		And User enters Password as "test@123"
		And User agree to the Privacy Policy
		And click on Continue button
		Then Validation popped up for duplicate account
	
@regression @Minor
	Scenario: Verify validation message for incorrect email address.
		Given User launch browser
		And opens URL "http://localhost/opencart/upload/index.php"
		When User navigates to MyAccount menu
		And click on Register
		And User enters First Name as "priyank" and Last Name as "s"
		And User enters E-mail as "priyankshah@gmail"
		And User enters Password as "test@123"
		And User agree to the Privacy Policy
		And click on Continue button
		Then Page displays validation for incorrect email address
		
@regression @Minor
	Scenario: Verify placeholder for all text fields.
		Given User launch browser
		And opens URL "http://localhost/opencart/upload/index.php"
		When User navigates to MyAccount menu
		And click on Register
		Then First Name field shows placeholder as "First Name"
		And Last Name field shows placeholder as "Last Name"
		And Email field shows placeholder as "E-Mail"
		And Password field shows placeholder as "Password"
		
		
		