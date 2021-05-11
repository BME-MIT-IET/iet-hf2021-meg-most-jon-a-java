Feature: Lapát használata
  Scenario: useLapat
    Given a Tabla with 4 snow
    And an Eszkimo with Lapat
    When the Eszkimo use Lapat on snowcovered field
    Then it should be 2 on the Table
