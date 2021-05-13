Feature: Sarkkutato Etel felvesz
  Scenario: felveszEtelSarkkutato
    Given a Tabla with a Etel
    And an SarkkutatoEtel
    When the Sarkkutato felvesz Etel
    Then Sarkkutato should have Etel