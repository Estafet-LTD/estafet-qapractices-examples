Feature: A bunch of wiki tests

  @Wiki
  Scenario: User searches Wikipedia for articles
    Given the user is on the home page
    And the user searches Wikipedia for "Thor"
    And the user opens the link for "Germanic mythology"
    When the user is redirected to the "Germanic mythology" article
    And the user opens the history menu
    Then the user can print the last "5" revision dates