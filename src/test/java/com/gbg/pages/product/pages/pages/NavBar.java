package com.gbg.pages.product.pages.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class NavBar {

    private final Page page;

    public NavBar(Page page) {
        this.page = page;
    }

    public void openCart() {
        page.getByTestId("nav-cart").click();
    }

    public void goToHomePage() {
        page.getByRole(AriaRole.MENUBAR).getByText("Home").click();
    }

    public void openHomePage() {
        page.navigate("https://practicesoftwaretesting.com/");
    }

    public void openContactPage() {
        page.navigate("https://practicesoftwaretesting.com/contact");
    }

}
