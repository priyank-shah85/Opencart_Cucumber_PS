Feature: Login Data Driven with Excel.

@sanity
	Scenario Outline: Successful login with valid credentials
		Given User launch browser
		And opens URL "http://localhost/opencart/upload/index.php"
		When User navigates to MyAccount menu
		And click on Login
		Then check User navigates to MyAccount Page by passing Email and Password with excel row "<row_index>"
		
		Examples:
			| row_index	|
			| 1 |
			| 2 |
			| 3 |
			| 4 |
			| 5 |