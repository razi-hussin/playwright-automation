package com.gbg.step_definitions.instinct.header;

import com.gbg.pages.instinct.header.HeaderPage;
import com.gbg.pages.instinct.investigation.NewApplicationPage;
import com.gbg.step_definitions.base.BaseSteps;
import com.gbg.utils.TestContext;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.And;

public class HeaderSteps {

    private final Page page;
    private final BaseSteps baseSteps;

    public HeaderSteps(TestContext context, BaseSteps baseSteps) {
        this.page = context.getPage();
        this.baseSteps = baseSteps;
    }

    @And("I navigate to Recent Application page")
    public void iNavigateToRecentApplicationPage() {
        baseSteps.iClickByLocator(".col-sm-1 > img");
        baseSteps.isDisplayed("Recent Applications", "HEADING");
    }

    @And("I logout from Instinct Hub")
    public void iLogoutFromInstinctHub() {
        baseSteps.iClick("administrator");
        baseSteps.iClick("Logout");
        baseSteps.isDisplayed("Log In", "HEADING");
    }

}
