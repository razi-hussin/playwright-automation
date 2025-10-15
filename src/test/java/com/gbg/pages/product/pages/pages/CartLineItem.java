package com.gbg.pages.product.pages.pages;

public record CartLineItem(
        String title,
        int quantity,
        double price,
        double total
) {}
