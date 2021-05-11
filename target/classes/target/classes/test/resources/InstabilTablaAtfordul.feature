
  Feature: Instabil tablan tul sokan vannak es atfordul

    Scenario: UnstableTableFlips
      Given There is a table
      And   a player
      When  There are too many players on the table
      Then  The players hp should be null