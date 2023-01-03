Feature: Searching products with various ways.

@sanity @regression @Critical
	Scenario Outline: Searching with key that returns multiple results.
		Given User launch browser
		And opens URL "http://localhost/opencart/upload/index.php"
		And user enters "<keyword>" word in search box
		And user clicks on Search icon
		Then page displays heading as "Products meeting the search criteria"
		And page title contains "<keyword>" word
		And page returns all products matching with search keyword "<keyword>"
		
		Examples:
		| keyword |
		| mac			|
		| air			|
		| i				|
		
@regression @Major
	Scenario Outline: Searching with key that returns no results.
		Given User launch browser
		And opens URL "http://localhost/opencart/upload/index.php"
		And user enters "<keyword>" word in search box
		And user clicks on Search icon
		Then page displays message as "There is no product that matches the search criteria."
		And page returns no products as search results
		
		Examples:
		| keyword |
		| test123	|
		| 				|
		
@sanity @regression @Major @temp
	Scenario: Verify the search within product name or product description.
		Given User launch browser
		And opens URL "http://localhost/opencart/upload/index.php"
		And user enters "mac" word in search box
		And user clicks on Search icon
		And user checks Search in product descriptions checkbox
		And user clicks on Search button
		Then page returns all products where "mac" is present either in product name or description
		

