package com.example.shopifycoding.ShopifyCodingChallenge.models;

import java.util.Objects;

/**
 * Product model - Every product should have a title, price, and inventory_count.
 */
public class Product {

    private String title;
    private double price;
    private int inventory_count;

    public Product() {
        this.title = "missing title";
        this.price = 0.0;
        this.inventory_count = 0;
    }

    public Product(String title, double price, int inventory_count) {
        this.title = title;
        this.price = price;
        this.inventory_count = inventory_count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInventory_count() {
        return inventory_count;
    }

    public void setInventory_count(int inventory_count) {
        this.inventory_count = inventory_count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Double.compare(product.getPrice(), getPrice()) == 0 &&
                getInventory_count() == product.getInventory_count() &&
                Objects.equals(getTitle(), product.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getPrice(), getInventory_count());
    }
}
