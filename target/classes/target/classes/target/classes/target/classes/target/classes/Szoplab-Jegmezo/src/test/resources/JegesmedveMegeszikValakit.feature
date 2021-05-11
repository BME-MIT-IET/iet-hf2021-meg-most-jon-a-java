


Feature: Egy jegesmedve megeszik valakit

  Scenario: PolarBearEatsSomeone

    Given a   table
    And   a  researcher
    And   polar bear
    When  the polar bear moves to the other table to eat the researcher
    Then  the researchers hp should be 0