@all
Feature: Watchlist Maintenance

  Scenario: Fraud checking from Watchlist Maintenance
    Given I login to Instinct Hub
    And I set watchlist counter in Recent Application page
    And I navigate to Watchlist Maintenance page
    And I go to New Watchlist page
    When I complete Demande form in New Watchlist page
    And I complete Emprunteur form in New Watchlist page
    Then "Saved successfully." is displayed
    And "NEGATIVE2025" is displayed in "Auto Check Results" page
    And "No records found." is displayed in "Auto Check Results" page

    And I navigate to Application Maintenance page
    And I go to New Application page
    When I complete Demande form in New Watchlist page
    And I complete Emprunteur form in New Watchlist page
    Then "Saved successfully." is displayed

    When I do fraud check in Application Record page
    And 1 matches record in Match Review page
    And "Rules Triggered 1" is displayed
    And "NEGATIVE2025" is displayed in "Match Review" page
    And I logout from Instinct Hub