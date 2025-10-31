package com.gbg.pages.base;

import com.gbg.utils.ConfigLoader;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.cucumber.java.en.And;

public class Base {

    private final Page page;

    public Base(Page page) {
        this.page = page;
    }

    public Locator ByRole(String role) {
        return page.getByRole(AriaRole.valueOf(role.toUpperCase()));
    }

    public Locator ByRole(String role, String name) {
        return page.getByRole(AriaRole.valueOf(role.toUpperCase()), new Page.GetByRoleOptions().setName(name)
                .setExact(true)).first();
    }

    public Locator ByRoles(String role1, String name, String role2) {
        return page.getByRole(AriaRole.valueOf(role1.toUpperCase()), new Page.GetByRoleOptions().setName(name)
                .setExact(true)).getByRole(AriaRole.valueOf(role2.toUpperCase()));
    }

}
