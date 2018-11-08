package com.codecool.servlet;

import java.util.ArrayList;
import java.util.List;

public class ItemStore {

    private static List<Item> webShopItems = new ArrayList<>();
    private static List<Item> shoppingCartItems = new ArrayList<>();

    static void appendWebShopItems (Item item) {
        webShopItems.add(item);
    }

    static void addToCart(Item item) {
        shoppingCartItems.add(item);
    }

    static void removeFromCart(Item item) {
        shoppingCartItems.remove(item);
    }

    public static List<Item> getWebShopItems() {
        return webShopItems;
    }

    public static List<Item> getShoppingCartItems() {
        return shoppingCartItems;
    }

}
