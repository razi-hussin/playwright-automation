@all
Feature: Navigation

  Scenario: Login and Logout
    Given I navigate to "Instinct" Hub
    And "Log In" "heading" is displayed
    When I enter "username"
    And I enter "password"
    And I click the "Log In" "button"
    And I click the "Close" "button"
    Then "Dashboard" "heading" is displayed

    When I click "administrator"
    And I click "Logout"
    Then "Log In" "heading" is displayed