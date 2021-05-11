Feature: Sátor használata
  Scenario: useSator
    Given a Tabla without buildings
    And an Eszkimo with a Sator
    When the Eszkimo use Sator
    Then the Sator should be felallítva and removed from Eszkimo