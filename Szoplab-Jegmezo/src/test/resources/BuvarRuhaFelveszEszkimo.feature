Feature: Eszkimo Buvarruha felvesz
  Scenario: felveszBuvarruhaEszkimo
    Given a Tabla with a Buvarruha
    And an Eszkimo
    When the Eszkimo felvesz Buvarruha
    Then Eszkimo should have Buvarruha