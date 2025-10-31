package com.gbg.step_definitions.instinct.rules;

import com.gbg.pages.instinct.rules.RulesDefinitionPage;
import com.gbg.pages.instinct.rules.RulesFilterPage;
import com.gbg.step_definitions.base.BaseSteps;
import com.gbg.utils.TestContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class RulesSteps {

    private final Page page;
    private final BaseSteps baseSteps;
    private final RulesDefinitionPage rulesDefinitionPage;
    private final RulesFilterPage rulesFilterPage;

    public RulesSteps(TestContext context, BaseSteps baseSteps) {
        this.page = context.getPage();
        this.baseSteps = baseSteps;
        this.rulesDefinitionPage = new RulesDefinitionPage(page);
        this.rulesFilterPage = new RulesFilterPage(page);
    }

    @And("I navigate to Rules Configuration page")
    public void iNavigateToRulesConfigurationPage() {
        baseSteps.iClick("Rules", "LISTITEM");
        baseSteps.iClickByLocator("//a[@class='menu-link'][text()='Rules Configuration']");
        baseSteps.isDisplayed("Rules Configuration", "HEADING");
    }

    @And("I go to Rules Definition page")
    public void iGoToRulesDefinitionPage() {
        baseSteps.iClick("Add", "BUTTON");
        baseSteps.isDisplayed("Rules Definition", "LINK");
        baseSteps.isDisplayed("Rules", "HEADING");
    }

    @When("I complete Rule Details form")
    public void iCompleteRuleDetailsForm() {
        baseSteps.iWaitForMiliseconds(2000);
        rulesDefinitionPage.setRuleCode("RC001");
        rulesDefinitionPage.setRuleDescription("IP Checking");
        rulesDefinitionPage.setRuleScore("100");
        baseSteps.iClick("Match Application To Itself");
    }

    @And("I complete Rule Conditions form")
    public void iCompleteRuleConditionsForm() {
        baseSteps.iClick("Rule Conditions");
        baseSteps.iClick("Add New Rule Line");
        baseSteps.iClick("Database Value");
        baseSteps.iClickByLocator("//ng-select[@formcontrolname='applicationCategoryNumber']");
        baseSteps.iClick("Emprunteur", "OPTION");
        baseSteps.iClickByLocator("//ng-select[@formcontrolname='applicationFieldNumber']");
        baseSteps.iClick("Adresse IP", "OPTION");
        baseSteps.iClickByLocator("//ng-select[@formcontrolname='operatorValue']");
        baseSteps.iClick("=", "OPTION");
        rulesDefinitionPage.setAssignmentValue("10.0.0.1");
        baseSteps.iClick("Add Rule", "BUTTON");
        baseSteps.isDisplayed("Database Emprunteur.Adresse IP = '10.0.0.1'");
    }

    @And("I save rule successfully")
    public void iSaveRuleSuccessfully() {
        baseSteps.iClick("Save", "BUTTON");
        baseSteps.iClick("Yes", "BUTTON");
        baseSteps.iClick("Yes", "BUTTON");
        baseSteps.iClick("Yes", "BUTTON");
        baseSteps.iClick("Floa", "CELL");
        baseSteps.iClick("Save", "BUTTON");
        baseSteps.isDisplayed("The rule has been added to the rule set.");
    }

    @And("I verify rule in Rules Configuration list")
    public void iVerifyRuleInRulesConfigurationList() {
        iNavigateToRulesConfigurationPage();
        baseSteps.iClick(" Filter", "BUTTON");
        rulesFilterPage.setRuleCode(rulesDefinitionPage.getRuleCodeValue());
        baseSteps.iClick("Apply", "BUTTON");
        baseSteps.isDisplayed(rulesDefinitionPage.getRuleCodeValue(), "CELL");
    }

    @And("I remove rule in Rules Configuration list")
    public void iRemoveRuleInRulesConfigurationList() {
        iVerifyRuleInRulesConfigurationList();
        baseSteps.iClickByLocator("//tr[@id='Row_0']");
        baseSteps.iClickByLocator("//div[@role='group']/button");
        baseSteps.iClick("Delete", "BUTTON");
        baseSteps.iClick("Yes", "BUTTON");
        baseSteps.isDisplayed("Following rule/s were deleted successfully: " + rulesDefinitionPage.getRuleCodeValue());
        baseSteps.iClick("OK", "BUTTON");
    }

    @And("I remove rule in Organisation Rule Sets")
    public void iRemoveRuleInOrganisationRuleSets() {
        baseSteps.iClick("Rules", "LISTITEM");
        baseSteps.iClick("Organisation Rule Sets");
        baseSteps.isDisplayed("Organisation Rule Sets", "HEADING");
        baseSteps.iClickByLocator("//div[@id='dropdownBasic2']");
        baseSteps.iClickByLocator("//li[normalize-space()='Change']");
        baseSteps.iClick(rulesDefinitionPage.getRuleCodeValue(), "CELL");
        baseSteps.iClickByLocator("//div[@class='button-col']/div/img[2]");
        baseSteps.iClick("Save", "BUTTON");
        baseSteps.isDisplayed("Updated Successfully");
    }

}
