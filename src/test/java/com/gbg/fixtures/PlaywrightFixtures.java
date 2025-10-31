package com.gbg.fixtures;

import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import com.gbg.utils.ConfigLoader;

import java.io.ByteArrayInputStream;
import java.nio.file.Paths;
import java.util.Arrays;

public class PlaywrightFixtures {

    private static final ThreadLocal<Playwright> playwright = ThreadLocal.withInitial( () -> {
        Playwright playwright = Playwright.create();
        playwright.selectors().setTestIdAttribute("data-Test");
        return playwright;
    });

    private static final ThreadLocal<Browser> browser = ThreadLocal.withInitial( () -> {
        String browserType = ConfigLoader.get("browser").toLowerCase();
        boolean isHeadless = ConfigLoader.getBoolean("headless.option");

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(isHeadless)
                .setArgs(Arrays.asList("--no-sandbox", "--disable-extensions", "--disable-gpu"));

        switch (browserType) {
            case "firefox":
                return playwright.get().firefox().launch(options);
            case "webkit":
                return playwright.get().webkit().launch(options);
            case "chromium":
            default:
                return playwright.get().chromium().launch(options);
        }
    });

    private static final ThreadLocal<BrowserContext> browserContext = new ThreadLocal<>();

    private static final ThreadLocal<Page> page = new ThreadLocal<>();

    public static void setUpBrowserContext() {
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
                .setViewportSize(1500, 720)
                .setIgnoreHTTPSErrors(true);

        browserContext.set(browser.get().newContext(contextOptions));
        page.set(browserContext.get().newPage());

        int navigationTimeout = Integer.parseInt(ConfigLoader.get("navigation.timeout"));
        int actionTimeout = Integer.parseInt(ConfigLoader.get("action.timeout"));
        page.get().setDefaultNavigationTimeout(navigationTimeout);
        page.get().setDefaultTimeout(actionTimeout);
    }

    public static void closeContext() {
        if (browserContext.get() != null) {
            browserContext.get().close();
            browserContext.remove();
        }
        page.remove();
    }

    public static void tearDown() {
        if (browser.get() != null) {
            browser.get().close();
            browser.remove();
        }

        if (playwright.get() != null) {
            playwright.get().close();
            playwright.remove();
        }
    }

    public static Page getPage() {
        return page.get();
    }

    public static BrowserContext getBrowserContext() {
        return browserContext.get();
    }

    public static void takeScreenshot(String name) {
        var screenshot = getPage().screenshot(
                new Page.ScreenshotOptions().setFullPage(true)
        );
        Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
    }

    public static void setupTrace() {
        getBrowserContext().tracing().start(
        new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true)
        );
    }

    public static void recordTrace(String testName) {
        getBrowserContext().tracing().stop(
                new Tracing.StopOptions()
                        .setPath(Paths.get("target/traces/trace-" + testName + ".zip"))
        );
    }

}
