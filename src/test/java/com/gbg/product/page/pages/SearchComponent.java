package com.gbg.product.page.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class SearchComponent {

    private final Page page;

    public SearchComponent(Page page) {
        this.page = page;
    }

    public void searchBy(String keyword) {
        page.getByPlaceholder("Search").fill(keyword);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search")).click();
        page.locator("text=" + keyword).waitFor(new Locator.WaitForOptions().setTimeout(60000));
    }

    public void clearSearch() {
        page.waitForResponse("**/products**", () -> {
            page.getByTestId("search-reset").click();
        });
    }

    public void filterBy(String filterName) {
        page.waitForResponse(response ->
                        response.url().contains("/products") && response.request().method().equals("GET"),
                () -> page.getByLabel(filterName).click()
        );
    }

    public void sortBy(String sortFilter) {
        page.waitForResponse(response ->
                        response.url().contains("/products") && response.request().method().equals("GET"),
                () -> page.getByTestId("sort").selectOption(sortFilter)
        );
    }

}
