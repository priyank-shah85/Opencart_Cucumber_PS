Feature: Verify details on Product Display page.

@sanity @regression @Critical
	Scenario: Verify the product main image.
		Given User launch browser
		And opens URL "http://localhost/opencart/upload/index.php"
		And clicks on first product from Featured list
		Then user redirects to main display page of selected product
		And user can see product large image
		
@regression @Major
	Scenario: Verify the Light Box view.
		Given User launch browser
		And opens URL "http://localhost/opencart/upload/index.php"
		And clicks on Cameras menu from top navigation bar
		And clicks on Nikon D300 product
		And user redirects to "Nikon D300" product details page
		And user can see product large image
		And user clicks on product large image
		Then user can see product image in Light Box view
		And user can view all four products with Next button
		And user can view all four products with Previous button
		And user clicks on close icon within Light Box view
		Then user redirects to "Nikon D300" product details page
		
@regression @Major
	Scenario: Verify adding a new review.
		Given User launch browser
		And opens URL "http://localhost/opencart/upload/index.php"
		And clicks on Tablets menu from top navigation bar
		And clicks on Samsung Galaxy Tab 10.1 product
		And user redirects to "Samsung Galaxy Tab 10.1" product details page
		And clicks on Reviews tab
		And user enters full name in Your Name field
		And user enters product feedback in Your Review field
		And user provides "4" ratings
		And user clicks on Continue
		Then page shows confirmation message "Thank you for your review. It has been submitted to the webmaster for approval." for submitting the review