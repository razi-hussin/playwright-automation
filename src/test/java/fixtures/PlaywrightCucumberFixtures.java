package fixtures;

import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import utils.ConfigLoader;

import java.io.ByteArrayInputStream;
import java.nio.file.Paths;
import java.util.Arrays;

public class PlaywrightCucumberFixtures {

    private static final ThreadLocal<Playwright> playwright = ThreadLocal.withInitial( () -> {
        Playwright playwright = Playwright.create();
        playwright.selectors().setTestIdAttribute("data-Test");
        return playwright;
    });

    private static final ThreadLocal<Browser> browser = ThreadLocal.withInitial( () -> {
        String browserType = ConfigLoader.get("browser", "chromium").toLowerCase();
        boolean isHeadless = ConfigLoader.getBoolean("headless.option", true);

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
        browserContext.set(browser.get().newContext());
        page.set(browserContext.get().newPage());
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
