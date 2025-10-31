package com.gbg.step_definitions.instinct.investigation;

import com.gbg.pages.instinct.header.HeaderPage;
import com.gbg.pages.instinct.investigation.NewApplicationPage;
import com.gbg.step_definitions.base.BaseSteps;
import com.gbg.utils.TestContext;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.And;

public class InvestigationSteps {

    private final Page page;
    private final BaseSteps baseSteps;
    private final HeaderPage headerPage;
    private final NewApplicationPage newApp;

    public InvestigationSteps(TestContext context, BaseSteps baseSteps) {
        this.page = context.getPage();
        this.baseSteps = baseSteps;
        this.headerPage = new HeaderPage(page);
        this.newApp = new NewApplicationPage(page);
    }

    @And("I set counter in Recent Application page")
    public void iSetCounterInRecentApplicationPage() {
        baseSteps.iClickByLocator(".col-sm-1 > img");
        baseSteps.isDisplayed("Recent Applications", "HEADING");
        baseSteps.iWaitForMiliseconds(5000);
        if (headerPage.getLatestID().first().isVisible()) {
            String trimmedLatestID = baseSteps.getCounter(headerPage.getLatestID().first().textContent());
            newApp.setCounter(trimmedLatestID);
        } else {
            newApp.setCounter("1001");
        }
    }

    @And("I navigate to Application Maintenance page")
    public void iNavigateToApplicationMaintenancePage() {
        baseSteps.iClick("Investigation", "LISTITEM");
        baseSteps.iClick("Applications", "BUTTON");
        baseSteps.iClick("Application Maintenance");
        baseSteps.isDisplayed("Application Maintenance", "HEADING");
    }

    @And("I go to New Application page")
    public void iGoToNewApplicationPage() {
        baseSteps.iClickByLocator("//div[@role='group']/button");
        baseSteps.iWaitForMiliseconds(2000);
        baseSteps.iClick("New Application", "BUTTON");
        baseSteps.isDisplayed("New Application", "HEADING");
    }

    @And("I complete Demande form in New Application page")
    public void iCompleteDemandeFormInNewApplicationPage() {
        baseSteps.isDisplayed("Demande 1");
        baseSteps.iClickByLocator("#record-control-1-1");
        baseSteps.iClick("FLA", "OPTION");
        newApp.setDemandeId("IDD" + newApp.getCounter());
        newApp.setDemandeDate("28/10/2025");
        baseSteps.iWaitForMiliseconds(2000);
        baseSteps.iClickByLocator("#record-control-1-9");
        baseSteps.iClick("CCA", "OPTION");
    }

    @And("I complete Emprunteur form in New Application page")
    public void iCompleteEmprunteurFormInNewApplicationPage() {
        baseSteps.iClickByLocator("(//h4[normalize-space()='Emprunteur']/ancestor::div[contains(@class, 'row')]//img)[1]");
        newApp.setEmprunteurIdentifiantClient("IDE" + newApp.getCounter());
        newApp.setEmprunteurNom("NOM" + newApp.getCounter());
        baseSteps.iClick("Save", "BUTTON");
    }

    @And("I update clone record in New Application page")
    public void iUpdateCloneRecordInNewApplicationPage() {
        baseSteps.isDisplayed("New Application", "HEADING");
        newApp.setDemandeIdUpdate("IDDU"  + newApp.getCounter());
        baseSteps.iClickByLocator("#record-control-1-9");
        baseSteps.iClick("CCS", "OPTION");
        baseSteps.iClick("Save", "BUTTON");
    }

    @And("I open record in Recent Application page")
    public void iOpenRecordInRecentApplicationPage() {
        baseSteps.iWaitForMiliseconds(2000);
        baseSteps.iDoubleClick(newApp.getDemandeIdValue(), "CELL");
        baseSteps.iWaitForMiliseconds(2000);
    }

    @And("I do fraud check in Application Record page")
    public void iDoFraudCheckInApplicationRecordPage() {
        baseSteps.isDisplayed("Application Record", "HEADING");
        baseSteps.iClickByLocator("//div[@role='group']/button");
        baseSteps.iWaitForMiliseconds(2000);
        baseSteps.iClick("Fraud Check", "BUTTON");
        baseSteps.iWaitForMiliseconds(2000);
    }

    @And("fraud check is successful")
    public void fraudCheckIsSuccessful() {
        baseSteps.isDisplayed("Fraud Check Successful", "HEADING");
        baseSteps.isDisplayed("Fraud check is now complete - no matches found.");
        baseSteps.iClick("OK", "BUTTON");
        baseSteps.iClick("Review", "BUTTON");
    }

    @And("I set proven fraud in Application Record page")
    public void iSetProvenFraudInApplicationRecordPage() {
        baseSteps.isDisplayed("Application Record", "HEADING");
        baseSteps.iClick("Review", "BUTTON");
        baseSteps.iWaitForMiliseconds(2000);
        baseSteps.iClickByLocator("//ng-select[contains(@class, 'ng-select')]");
        baseSteps.iWaitForMiliseconds(2000);
        baseSteps.iClick("Emprunteur", "OPTION");
        baseSteps.iClick("Action", "BUTTON");
        baseSteps.iClick("Fraude avérée", "BUTTON");
    }

    @And("I clone record in Application Record page")
    public void iCloneRecordInApplicationRecordPage() {
        baseSteps.isDisplayed("Application Record", "HEADING");
        baseSteps.iWaitForMiliseconds(2000);
        baseSteps.iClickByLocator("//div[@role='group']/button");
        baseSteps.iClick("Clone", "BUTTON");
    }

    @And("{int} matches record in Match Review page")
    public void matchesRecordInMatchReviewPage(int value) {
        baseSteps.isDisplayed("Match Review", "LINK");
        baseSteps.isDisplayed("ID (TBD) - " + newApp.getDemandeIdValue(), "HEADING");
        baseSteps.isDisplayed(" Value Matches (" + value + ") ");
    }

    @And("I confirm fraud in Action page")
    public void iConfirmFraudInActionPage() {
        baseSteps.iClickByLocator("#decisionReason");
        baseSteps.iClick("Origine - Alertes automatiques");
        baseSteps.iClick("Continue", "BUTTON");
        baseSteps.iClickByLocator("#natureoffraud");
        baseSteps.iClick("- Fraudeur (blocage partiel)");
        baseSteps.iClick("Continue", "BUTTON");
        baseSteps.iClick("Confirm", "BUTTON");
    }

    @And("I confirm matched record as fraud in Action page")
    public void iConfirmMatchedRecordAsFraudInActionPage() {
        baseSteps.iClick("Action", "BUTTON");
        baseSteps.iClick("Fraude avérée", "BUTTON");
        baseSteps.iClick("Continue", "BUTTON");
        baseSteps.iClickByLocatorAndRole("#natureoffraud", "TEXTBOX");
        baseSteps.iClickByLabelAndText("Options list", "- Fraudeur (blocage partiel)");
        baseSteps.iClick("Continue", "BUTTON");
        baseSteps.iClick("Confirm", "BUTTON");
    }

    @And("{string} is displayed in {string} page")
    public void isDisplayedInPage(String text, String pg) {
        baseSteps.isDisplayed(pg, "HEADING");
        if (text.equalsIgnoreCase("NEGATIVE2025")) {
            String yyyymmdd = baseSteps.getCurrentDateInYYYYMMDD();
            text = text + yyyymmdd;
        }
        baseSteps.isDisplayed(text);
    }

}
