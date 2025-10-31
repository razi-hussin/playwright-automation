package com.gbg.pages.instinct.login;

import com.gbg.pages.base.Base;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage extends Base {

    private final Locator username;
    private final Locator password;

    public LoginPage(Page page) {
        super(page);
        this.username = page.getByPlaceholder("User Id");
        this.password = page.getByPlaceholder("Password");
    }

    public void setUsername(String value) {
        username.fill(value);
    }

    public void setPassword(String value) {
        password.fill(value);
    }

}
