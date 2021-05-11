Feature: Vízbe esés
  Scenario: FallInWater
    Given a Viz
    And an Eszkimo without Buvarruha
    When the Eszkimo fall in the Viz
    Then the eszkimo's enery should be zero