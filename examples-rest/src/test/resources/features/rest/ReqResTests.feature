Feature: ReqRes tests

  @Rest
  Scenario: Create user
    Given a user lists all available users
    When user "Pesho Peshov" doesn't exist
    Then the user create a new user "Pesho Peshov"

  @Rest
  Scenario: Update user
    Given a user lists all available users
    When user "George Bluth" exist
    Then the user can be updated to "George BluthDidNiveEleven"