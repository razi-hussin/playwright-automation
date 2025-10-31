package com.gbg.step_definitions.hook;

import com.gbg.fixtures.PlaywrightFixtures;
import com.gbg.utils.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Step;
import com.gbg.utils.ConfigLoader;

import static com.gbg.fixtures.PlaywrightFixtures.takeScreenshot;

public class Hooks {

    private final TestContext context; //

    public Hooks(TestContext context) { //
        this.context = context; //
    } //

    @Step("Initialize")
    @Before(order = 0)
    public void setUp() {
        PlaywrightFixtures.setUpBrowserContext();
        context.setPage(PlaywrightFixtures.getPage()); //
        if (ConfigLoader.getBoolean("trace.option")) {
            PlaywrightFixtures.setupTrace();
        }
    }

    @Step("Clean up")
    @After
    public void tearDown(Scenario scenario) {
        if (ConfigLoader.getBoolean("trace.option")) {
            String testName = scenario.getName().replaceAll("\\s+", "_");
            PlaywrightFixtures.recordTrace(testName);
        }
        PlaywrightFixtures.closeContext();
        PlaywrightFixtures.tearDown();
    }

    @AfterStep
    public void afterEachStep() {
        takeScreenshot("Step Screenshot");
    }

}
