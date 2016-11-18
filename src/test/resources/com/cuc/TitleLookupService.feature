Feature: Find a Title given id
  Scenario: Find a Title given id
    Given I use the titlelookup service
    When I enter the id 5
    Then the title eaque aut omnis a should show up

  Scenario: Find a Title given invalid id
    Given I use the titlelookup service
    When I enter the id -5
    Then the title empty should show up

  Scenario: check message following error connecting to service
    Given I use incorrect uri for titlelookup service
    When I enter the id 5
    Then the user gets an IO Exception

