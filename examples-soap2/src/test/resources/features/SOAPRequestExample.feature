Feature: SOAP Example

  Scenario Outline: Get all cities in a country
    Given cities for <country> are requested
    When request is sent
    Then verify list of cities <cities> is returned
    Examples:
      | country  | cities                                                            |
      | Bulgaria | Burgas, Plovdiv, Rousse, Sofia Observ., Varna                     |
      | Ireland  | Cork Airport, Dublin Airport, Casement Aerodrome, Shannon Airport |
