Feature: Login with valid credentials.

@sanity @regression @happypath @Critical
	Scenario: Successful login with valid credentials
		Given User launch browser
		And opens URL "http://localhost/opencart/upload/index.php"
		When User navigates to MyAccount menu
		And click on Login
		And User enters email as "priyankshah1@gmail.com" and password as "test@123"
		And click on Login button
		Then User navigates to MyAccount page
		And User navigates to MyAccount menu
		And click on Logout

@regression @Critical @temp
	Scenario Outline: Verify warning messages for invalid credentials.
		Given User launch browser
		And opens URL "http://localhost/opencart/upload/index.php"
		When User navigates to MyAccount menu
		And click on Login
		And User enters email as "<email>" and password as "<password>"
		And click on Login button
		Then Page shows warning message for invalid credentials
		
		Examples:
		| email									| password	|
		| priyankshah@gmail.com	| Test1234	|
		| test12@test.com				| test@123	|
		| t@gmail.com						| Test12345 |