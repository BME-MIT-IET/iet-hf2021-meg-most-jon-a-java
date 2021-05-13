

  Feature: Jatekos energiaja csokken mozgas utan

    Scenario: PlayersEnergyShouldDecreaseAfterMooving

      Given there are two tables
      And a player character
      When the player moves from one table to the other
      Then the players energy should decrease
