package step_definitions.hook;

import fixtures.PlaywrightCucumberFixtures;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Step;
import utils.ConfigLoader;

import static fixtures.PlaywrightCucumberFixtures.takeScreenshot;

public class CucumberHooks {

    @Step("Initialize")
    @Before(order = 0)
    public void setUp() {
        PlaywrightCucumberFixtures.setUpBrowserContext();

        if (ConfigLoader.getBoolean("trace.option", false)) {
            PlaywrightCucumberFixtures.setupTrace();
        }

    }

    @AfterStep
    public void afterEachStep() {
        takeScreenshot("Step Screenshot");
    }

    @Step("Clean up")
    @After
    public void tearDown(Scenario scenario) {
        if (ConfigLoader.getBoolean("trace.option", false)) {
            String testName = scenario.getName().replaceAll("\\s+", "_");
            PlaywrightCucumberFixtures.recordTrace(testName);
        }

        PlaywrightCucumberFixtures.closeContext();
        PlaywrightCucumberFixtures.tearDown();
    }

}
