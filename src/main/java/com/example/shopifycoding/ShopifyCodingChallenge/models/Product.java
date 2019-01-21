package com.example.shopifycoding.ShopifyCodingChallenge.models;

import java.util.Objects;

/**
 * Product model - Every product should have a title, price, and inventory_count.
 */
public class Product {

    private int id;
    private String title;
    private double price;
    private int inventory_count;

    public Product(int id, String title, double price, int inventory_count) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.inventory_count = inventory_count;
    }

    /**
     * Accessors and mutators
     */

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getInventory_count() {
        return inventory_count;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setInventory_count(int inventory_count) {
        this.inventory_count = inventory_count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getId() == product.getId() &&
                Double.compare(product.getPrice(), getPrice()) == 0 &&
                getInventory_count() == product.getInventory_count() &&
                Objects.equals(getTitle(), product.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getPrice(), getInventory_count());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", inventory_count=" + inventory_count +
                '}';
    }

    public Boolean checkout() {
        if (inventory_count > 0) {
            this.inventory_count--;
            return true;
        } else {
            return false;
        }
    }
}
