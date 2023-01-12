Feature: Verify end to end scenarios.

@sanity @regression @Critical
	Scenario: Verify end to end use case of ordering product with new user registration.
		Given User launch browser
		And opens URL "http://localhost/opencart/upload/index.php"
		And User navigates to MyAccount menu
		And click on Register
		And User enters First Name as "priyank" and Last Name as "shah23"
		And User enters E-mail as "ps23@gmail.com"
		And User enters Password as "test@123"
		And User agree to the Privacy Policy
		And click on Continue button
		Then User navigates to Account Confirmation page
		And user enters "Samsung SyncMaster" word in search box
		And user clicks on Search icon
		And clicks on "Samsung SyncMaster 941BW" product
		And user redirects to "Samsung SyncMaster 941BW" product details page
		And note down the product unit price
		And user updates quantity to "2"
		And clicks on Add to Cart button
		And clicks on Shopping Cart button from top of the page
		And verify that product "Samsung SyncMaster 941BW" is present in the cart
		And verify the total unit price based on quantity
		And user expands Estimate Shipping & Taxes section
		And user selects Country as "India"
		And user selects State as "Gujarat"
		And user enters Post Code as "382481"
		And clicks on Get Quotes button
		Then a pop up opens with Shipping method options with title "Shipping method options"
		And user selects very first shipping method
		And clicks on Apply Shipping button
		And note down the Total Price
		And clicks on Checkout button
		And user redirects to "Checkout" page
		And user enters First Name as "Priyank"
		And user enters Last Name as "Shah"
		And user enters Address 1 as "Gota"
		And user enters City as "Ahmedabad"
		And user selects "India" country
		And user selects "Gujarat" state
		And clicks on Continue button
		And verify that Shipping Method is already prepopulated
		And user selects Payment Method as "Cash On Delivery"
		And verify the Total Price is same as of Shopping Cart page
		And clicks on Confirm Order button
		Then user can see the confirmation message as "Your order has been placed!"
		And user clicks on Continue button