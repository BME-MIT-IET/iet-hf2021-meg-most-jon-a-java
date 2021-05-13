
  Feature: A sarkkutato kepesseget hasznal egy tablan

    Scenario: ResearcherUsedSkill

      Given a table
      And   a researcher
      When  the researcher uses it's skill
      Then  it's energy should decrease
      And   and the process should logged into the log file