

  Feature: Eszkimo kepesseget hasznal egy tablan

    Scenario: EskimoUsedSkill

      Given a  table
      And   an eskimo
      When  the eskimo uses it's skill
      Then  it's  energy should decrease
      And   and  the process should logged into the log file