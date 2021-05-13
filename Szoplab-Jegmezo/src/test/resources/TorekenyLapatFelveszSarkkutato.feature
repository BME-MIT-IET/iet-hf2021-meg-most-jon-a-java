Feature: Sarkkutato TorekenyLapat felvesz
  Scenario: felveszTorekenyLapatSarkkutato
    Given a Tabla with a TorekenyLapat
    And an SarkkutatoTorekenyLapat
    When the Sarkkutato felvesz TorekenyLapat
    Then Sarkkutato should have TorekenyLapat