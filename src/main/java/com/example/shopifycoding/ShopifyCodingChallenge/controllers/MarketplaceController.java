package com.example.shopifycoding.ShopifyCodingChallenge.controllers;

import com.example.shopifycoding.ShopifyCodingChallenge.Marketplace;
import com.example.shopifycoding.ShopifyCodingChallenge.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Used to communicate with the frontend
 */
public class MarketplaceController {

    private Marketplace marketplace = Marketplace.getMarketplace();

    @GetMapping("/getAll")
    public List<Product> getAllProduct() {
        return new ArrayList<>(marketplace.getIdentityMap().values());
    }

    @PostMapping("/getById")
    public Product getById(@RequestBody int id){
        try {
            return marketplace.getById(id);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/checkout")
    public Boolean checkout(@RequestBody List<Product> cart){
        return marketplace.checkout(cart);
    }

}
