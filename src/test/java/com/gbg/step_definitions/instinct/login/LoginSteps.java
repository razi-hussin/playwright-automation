package com.gbg.step_definitions.instinct.login;

import com.gbg.pages.instinct.login.LoginPage;
import com.gbg.step_definitions.base.BaseSteps;
import com.gbg.utils.ConfigLoader;
import com.gbg.utils.TestContext;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.And;

public class LoginSteps {

    private final Page page;
    private final BaseSteps baseSteps;
    private final LoginPage loginPage;

    public LoginSteps(TestContext context, BaseSteps baseSteps) {
        this.page = context.getPage();
        this.baseSteps = baseSteps;
        this.loginPage = new LoginPage(page);
    }

    @And("I enter {string}")
    public void iEnter(String value) {
        String credential;
        switch (value.toLowerCase()) {
            case "username":
                credential = ConfigLoader.get("instinct.username");
                loginPage.setUsername(credential);
                break;
            case "password":
                credential = ConfigLoader.get("instinct.password");
                loginPage.setPassword(credential);
                break;
            default:
                throw new IllegalArgumentException("Unknown credential type: " + value);
        }
    }

    @And("I login to Instinct Hub")
    public void iLoginToInstinctHub() {
        baseSteps.iNavigateToHub("instinct");
        baseSteps.isDisplayed("Log In", "HEADING");
        iEnter("username");
        iEnter("password");
        baseSteps.iClick("Log In", "BUTTON");
        baseSteps.iClick("Close", "BUTTON");
        baseSteps.isDisplayed("Dashboard", "HEADING");
    }

}