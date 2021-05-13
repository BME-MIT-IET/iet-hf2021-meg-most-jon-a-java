Feature: Eszkimo Kotel felvesz
  Scenario: felveszKotelEszkimo
    Given a Tabla with a Kotel
    And an Eszkimo Kotel
    When the Eszkimo felvesz Kotel
    Then Eszkimo should have Kotel