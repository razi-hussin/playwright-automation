package com.gbg.pages.product.pages.pages;

import com.microsoft.playwright.Page;
import com.gbg.pages.product.pages.domain.ProductSummary;

import java.util.List;

public class ProductList {
    private final Page page;

    public ProductList(Page page) {
        this.page = page;
    }

    public List<String> getProductNames()  {
        page.waitForTimeout(5000);
        return page.getByTestId("product-name").allInnerTexts();
    }

    public List<ProductSummary> getProductSummaries() {
        page.waitForTimeout(5000);
        return page.locator(".card").all()
                .stream()
                .map(productCard -> {
                    String productName = productCard.getByTestId("product-name").textContent().trim();
                    String productPrice = productCard.getByTestId("product-price").textContent().trim();
                    return new ProductSummary(productName, productPrice);
                }).toList();
    }

    public void viewProductDetails(String productName) {
        page.waitForTimeout(5000);
        page.locator(".card").getByText(productName).click();
    }

    public String getSearchCompletedMessage() {
        page.waitForTimeout(5000);
        return  page.getByTestId("search_completed").textContent();
    }
}