Feature: létezik e eszkimo

  Scenario: isEszkimo
    Given There is a Map and an Eskimo
    When I run the test
    Then an Eskimo should be on the Map
