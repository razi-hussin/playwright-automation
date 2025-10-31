package com.gbg.pages.instinct.header;

import com.gbg.pages.base.Base;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HeaderPage extends Base {

    private final Locator latestID;
    private final Locator latestIDW;

    public HeaderPage(Page page) {
        super(page);
        this.latestID = page.locator("(//td[starts-with(text(),'IDD1')])[1]");
        this.latestIDW = page.locator("//td[starts-with(text(),' IDDW1')]");
    }

    public Locator getLatestID() {
        return latestID;
    }

    public Locator getLatestIDW() {
        return latestIDW;
    }

}
