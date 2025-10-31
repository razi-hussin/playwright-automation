package com.gbg.utils;

import com.microsoft.playwright.Page;

public class TestContext {

    private Page page;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

}
