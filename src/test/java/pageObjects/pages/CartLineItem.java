package pageObjects.pages;

public record CartLineItem(
        String title,
        int quantity,
        double price,
        double total
) {}
