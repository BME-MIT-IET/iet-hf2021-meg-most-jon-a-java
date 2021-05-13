Feature: Eszkimo Lapat felvesz
  Scenario: felveszLapatEszkimo
    Given a Tabla with a Lapat
    And an Eszkimo Lapat
    When the Eszkimo felvesz Lapat
    Then Eszkimo should have Lapat