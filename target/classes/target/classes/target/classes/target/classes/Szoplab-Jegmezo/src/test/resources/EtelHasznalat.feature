Feature: Étel használata
  Scenario: useEtel
    Given a Tabla
    And an Eszkimo with 3 hp and an Etel
    When the Eszkimo use Etel
    Then it's hp should be 4 on the Table