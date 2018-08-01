Feature: SOAP request with Rest Assured
@soap
Scenario Outline: scenario description
    Given a request with bank code <bank_code> is send
    Then bank name in response xml should be <bank_name>

        Examples:
        | bank_code | bank_name      |
        | 10120800  | VON            |
        | 45261547  | Volksbank      |
        | 50662299  | Raiffeisenbank |