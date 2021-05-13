Feature: Sarkkutato PisztolyAlkatresz felvesz
  Scenario: felveszPisztolyAlkatreszSarkkutato
    Given a Tabla with a PisztolyAlkatresz
    And an SarkkutatoPisztolyAlkatresz
    When the Sarkkutato felvesz PisztolyAlkatresz
    Then Sarkkutato should have PisztolyAlkatresz