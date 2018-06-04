Feature: RestApi testing 

@rest 
Scenario: sending a GET request 
	Given user send a get request 
	And the status for get request is valid 
	
@rest 
Scenario: Sending a POST request 
	Given user list all users 
	When user send a post request to create "Kobe Bryant" 
	Then chek the status for post request is valid 
	
@rest  
Scenario: sending a PUT request 
	Given user list all users 
	When user check if "Janet Weaver" as user exist and collect user ID 
	Then user can be updated to "Janet Jackson" 
	
@rest 	
Scenario: sending a DELETE request 
	Given  user list all users
	When user check if "Emma Wong" as user exist and collect user ID  
	Then user can be deleted   
	
@rest1 
Scenario: sending a GET request and verify the status code
	Given user send a get request and status is valid    