Feature: Soap Example

# Background:
@Rest123
  Scenario Outline:
    When I get cities by <country>
    Then I validate the response

    Examples:
      | country  |
      | Bulgaria |