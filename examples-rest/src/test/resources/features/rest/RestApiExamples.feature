Feature: ReST API example tests

  @Rest123
  Scenario: An user can send GET requests
    Given an user sends a GET request
    And the response fot method "get" is valid


  @Rest
  Scenario: An user can send POST requests
    Given an user sends a POST request with the following parameters
      | User name  | User 1      |
      | Length     | 20          |
      | Cup size   | B-C         |
      | Measures   | 170-90-100  |
    And the response fot method "post" is valid
    