Feature: Login with valid credentials.

@sanity
	Scenario: Successful login with valid credentials
		Given User launch browser
		And opens URL "http://localhost/opencart/upload/index.php"
		When User navigates to MyAccount menu
		And click on Login
		And User enters email as "priyankshah@gmail.com" and password as "test@123"
		And click on Login button
		Then User navigates to MyAccount page