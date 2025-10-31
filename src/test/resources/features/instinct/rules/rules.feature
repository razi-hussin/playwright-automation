@all
Feature: Watchlist Maintenance

  Scenario: Adding new rule
    Given I login to Instinct Hub
    And I navigate to Rules Configuration page
    And I go to Rules Definition page
    When I complete Rule Details form
    And I complete Rule Conditions form
    Then I save rule successfully
    And I verify rule in Rules Configuration list
    And I remove rule in Organisation Rule Sets
    And I remove rule in Rules Configuration list
    And I logout from Instinct Hub