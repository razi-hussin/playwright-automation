@all
Feature: Application Maintenance

  Scenario: Fraud checking from Application Maintenance
    Given I login to Instinct Hub
    And I set counter in Recent Application page
    And I navigate to Application Maintenance page
    And I go to New Application page
    When I complete Demande form in New Application page
    And I complete Emprunteur form in New Application page
    Then "Saved successfully." is displayed

    When I do fraud check in Application Record page
    Then fraud check is successful
    And 0 matches record in Match Review page

    Given I navigate to Recent Application page
    And I open record in Recent Application page
    When I set proven fraud in Application Record page
    And I confirm fraud in Action page
    Then "No records found." is displayed in "Auto Check Results" page

    Given I navigate to Recent Application page
    And I open record in Recent Application page
    When I clone record in Application Record page
    And I update clone record in New Application page
    Then "Saved successfully." is displayed

    When I do fraud check in Application Record page
    Then 1 matches record in Match Review page
    And "Rules Triggered 1" is displayed
    And "Fraude avérée" is displayed

    When I confirm matched record as fraud in Action page
    Then "No records found." is displayed in "Auto Check Results" page
    And I logout from Instinct Hub