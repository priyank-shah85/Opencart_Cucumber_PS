Feature: Login Data Driven.

@regression @Major
	Scenario Outline: Successful login with valid credentials
		Given User launch browser
		And opens URL "http://localhost/opencart/upload/index.php"
		When User navigates to MyAccount menu
		And click on Login
		And User enters email as "<email>" and password as "<password>"
		And click on Login button
		Then User navigates to MyAccount page
		
		Examples:
			| email										| password	|
			|	priyankshah@gmail.com 	| test@123	|
			| abcxyz@gmail.com				|	test@123	|
			| abc@gmail.com						| test@123 	|