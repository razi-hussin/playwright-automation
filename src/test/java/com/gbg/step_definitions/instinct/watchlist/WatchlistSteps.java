package com.gbg.step_definitions.instinct.watchlist;

import com.gbg.pages.instinct.header.HeaderPage;
import com.gbg.pages.instinct.watchlist.NewWatchlistPage;
import com.gbg.step_definitions.base.BaseSteps;
import com.gbg.utils.ConfigLoader;
import com.gbg.utils.TestContext;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.And;

public class WatchlistSteps {

    private final Page page;
    private final BaseSteps baseSteps;
    private final HeaderPage headerPage;
    private final NewWatchlistPage watchlist;

    public WatchlistSteps(TestContext context, BaseSteps baseSteps) {
        this.page = context.getPage();
        this.baseSteps = baseSteps;
        this.headerPage = new HeaderPage(page);
        this.watchlist = new NewWatchlistPage(page);
    }

    @And("I set watchlist counter in Recent Application page")
    public void iSetCounterInRecentApplicationPage() {
        baseSteps.iClickByLocator(".col-sm-1 > img");
        baseSteps.isDisplayed("Recent Applications", "HEADING");
        baseSteps.iClickByLocator("//li[@class='gbg-nav-item']/a[text()='Watchlist']");
        baseSteps.iWaitForMiliseconds(5000);
        if (headerPage.getLatestIDW().first().isVisible()) {
            String trimmedLatestID = baseSteps.getCounter(headerPage.getLatestIDW().first().textContent());
            watchlist.setCounter(trimmedLatestID);
        } else {
            watchlist.setCounter("1001");
        }
    }

    @And("I navigate to Watchlist Maintenance page")
    public void iNavigateToWatchlistMaintenancePage() {
        baseSteps.iClick("Watchlist", "LISTITEM");
        baseSteps.iClick("Watchlist Maintenance");
        baseSteps.isDisplayed("Watchlist Maintenance", "HEADING");
    }

    @And("I go to New Watchlist page")
    public void iGoToNewWatchlistPage() {
        baseSteps.iClickByLocator("//div[@role='group']/button");
        baseSteps.iWaitForMiliseconds(2000);
        baseSteps.iClick("New Watchlist", "BUTTON");
        baseSteps.isDisplayed("New Watchlist", "HEADING");
    }

    @And("I complete Demande form in New Watchlist page")
    public void iCompleteDemandeFormInNewWatchlistPage() {
        baseSteps.isDisplayed("Demande 1");
        baseSteps.iClickByLocator("#record-control-1-1");
        baseSteps.iClick("FLA", "OPTION");
        watchlist.setDemandeId("IDDW" + watchlist.getCounter());
        watchlist.setDemandeDate("28/10/2025");
        baseSteps.iWaitForMiliseconds(2000);
        baseSteps.iClickByLocator("#record-control-1-9");
        baseSteps.iClick("CCA", "OPTION");
    }

    @And("I complete Emprunteur form in New Watchlist page")
    public void iCompleteEmprunteurFormInNewWatchlistPage() {
        baseSteps.iClickByLocator("(//h4[normalize-space()='Emprunteur']/ancestor::div[contains(@class, 'row')]//img)[1]");
        watchlist.setEmprunteurIdentifiantClient("IDEW" + watchlist.getCounter());
        watchlist.setEmprunteurNom("NOMW" + watchlist.getCounter());
        baseSteps.iClick("Save", "BUTTON");
    }

}
