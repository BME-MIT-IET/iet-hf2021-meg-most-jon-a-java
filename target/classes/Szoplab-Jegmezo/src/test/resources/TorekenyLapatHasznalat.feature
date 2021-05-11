Feature: Törékeny lapát használata
  Scenario: useTorekenyLapat
    Given a Tabla with 4 snowlayer
    And an Eszkimo with TorekenyLapat
    When the Eszkimo use TorekenyLapat on snowcovered field
    Then it should be 0 on the Table and the TorekenyLapat should be removed from inventory