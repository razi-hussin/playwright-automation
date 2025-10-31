package com.gbg.step_definitions.base;

import com.gbg.pages.base.Base;
import com.gbg.utils.ConfigLoader;
import com.gbg.utils.TestContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class BaseSteps {

    private final Page page;
    private final Base base;

    public BaseSteps(TestContext context) {
        this.page = context.getPage();
        this.base = new Base(page);
    }

    @Given("I navigate to {string} Hub")
    public void iNavigateToHub(String hub) {
        String url = switch (hub.toLowerCase()) {
            case "instinct" -> ConfigLoader.get("instinct.url");
            case "predator" -> ConfigLoader.get("predator.url");
            default -> throw new IllegalArgumentException("Unknown hub: " + hub);
        };
        page.navigate(url);
    }

    @And("I click the {string} {string}")
    public void iClick(String name, String role) {
        base.ByRole(role, name).click();
    }

    @And("I click {string}")
    public void iClick(String name) {
        page.getByText(name).click();
    }

    @And("I click by locator {string}")
    public void iClickByLocator(String locator) {
        page.locator(locator).click();
    }

    @And("I double click {string} {string}")
    public void iDoubleClick(String name, String role) {
        base.ByRole(role, name).dblclick();
    }

    @And("I click by locator {string} and role {string}")
    public void iClickByLocatorAndRole(String locator, String role) {
        page.locator(locator).getByRole(AriaRole.valueOf(role.toUpperCase())).click();
    }

    @And("I click by label {string} and text {string}")
    public void iClickByLabelAndText(String label, String text) {
        page.getByLabel(label).getByText(text).click();
    }

    @And("{string} {string} is displayed")
    public void isDisplayed(String name, String role) {
        base.ByRole(role, name).isVisible();
    }

    @And("{string} is displayed")
    public void isDisplayed(String text) {
        page.getByText(text).isVisible();
    }

    @And("I wait for {int} miliseconds")
    public void iWaitForMiliseconds(int sec) {
        page.waitForTimeout(sec);
    }

    public String getCurrentDateInYYYYMMDD() {
        return java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public String getCounter(String counter) {
        String numericPart = counter.replaceAll("[^0-9]", "");
        int incremented = Integer.parseInt(numericPart) + 1;
        return String.valueOf(incremented);
    }

}
