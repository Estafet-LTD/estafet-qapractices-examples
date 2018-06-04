Feature: ReqRes tests

  @Test
  Scenario: Create user
    Given a user lists all available users
    When user "Pesho Peshov" doesn't exist
    Then the user create a new user "Pesho Peshov"

  @Test
  Scenario: Update user
    Given a user lists all available users
    When user "George Bluth" exist
    Then the user can be updated to "George BluthDidNiveEleven"
   @Test 
   Scenario: Delete user
       Given a user lists all available users
       And the user check if user "Janet Weaver" exist 
       Then the user delete a new user by ID
    @Rest2   
    Scenario: Delete user with hook
    Given a user lists all available users
    And the user check if user "Byron Fields" exist
       
       