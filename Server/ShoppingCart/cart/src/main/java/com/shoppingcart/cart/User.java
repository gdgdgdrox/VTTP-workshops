package com.shoppingcart.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable{
    private String name;
    private String item;
    private Map<String, Integer> cart;

    public User(){
        this.cart = new HashMap<>();
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItem() {
        return this.item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Map<String,Integer> getCart() {
        return this.cart;
    }

    public void setCart(Map<String,Integer> cart) {
        this.cart = cart;
    }


    public Map<String, Integer> updateUserCart(String item){
        Map<String, Integer> userCart = this.getCart();
        if (userCart.containsKey(item)){
            Integer updatedQuantity = userCart.get(item).intValue() + 1;
            userCart.replace(item, updatedQuantity);
            return userCart;
        }
        else {
            userCart.put(item, 1);
            return userCart;
        }
    }
}


