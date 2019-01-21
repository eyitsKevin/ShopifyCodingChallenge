package com.example.shopifycoding.ShopifyCodingChallenge;

import com.example.shopifycoding.ShopifyCodingChallenge.gateways.ProductGateway;
import com.example.shopifycoding.ShopifyCodingChallenge.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Marketplace {

    /**
     * Singleton pattern
     */
    private static Marketplace marketplace;

    private List<Product> cart;
    private Map<Integer,Product> identityMap;

    private ProductGateway productGateway = ProductGateway.getProductGateway();
    private boolean isDatabaseChange;

    private Marketplace() {
        cart = new ArrayList<>();
        populateIdentityMap();
    }

    public static Marketplace getMarketplace() {
        if (marketplace == null) {
            marketplace = new Marketplace();
        }
        return marketplace;
    }

    public void populateIdentityMap() {
        List<Product> productList = productGateway.getAll();
        identityMap =  productList.stream().collect(Collectors.toMap(Product::getId, Function.identity()));
    }

    public Product getById(int id) {
        return productGateway.getById(id);
    }

    /**
     * Retrieve the current IdentityMap
     * If a change was made to the database, it will refresh
     * the identity map and set back the boolean to false
     * @return Map<Long, Product>
     */
    public Map<Integer, Product> getIdentityMap() {
        if(isDatabaseChange) {
            populateIdentityMap();
            isDatabaseChange = false;
        }
        return identityMap;
    }

    /**
     * Synchronized method that checks every item and compare it to the
     * in memory data. If found it decrements the inventory count.
     * This updates the database. If not found, the identity map gets repopulated
     * and the item is recheck again.
     * @param cart
     * @return
     */
    public  Boolean checkout(List<Product> cart) {
        for (Product item : cart) {
            if (identityMap.containsValue(item)){
                if(!item.checkout()) {
                    System.out.println("Cannot be purchased aborting...");
                    return false;
                }
                productGateway.update(item);
            } else { //repopulate the identity map and check again
                populateIdentityMap();
                if(!item.checkout()) { //is not able to checkout quantity lower than 0
                    System.out.println("Cannot be purchased aborting...");
                    return false;
                }
            }
        }
        isDatabaseChange = true;
        return true;
    }

}
