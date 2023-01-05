Feature: Verify Product Comparison use cases.

@sanity @regression @Critical @temp
	Scenario: Searching with key that returns multiple results.
		Given User launch browser
		And opens URL "http://localhost/opencart/upload/index.php"
		And user enters "<keyword>" word in search box
		And user clicks on Search icon
		Then page displays heading as "Products meeting the search criteria"
		And page title contains "<keyword>" word
		And page returns all products matching with search keyword "<keyword>"