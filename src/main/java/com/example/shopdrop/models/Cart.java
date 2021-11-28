package com.example.shopdrop.models;

public class Cart {

    private int cartItemId;
    private int quantity;
    private int userId;
    private int productId;

    public Cart(int cartItemId, int quantity, int userId, int productId) {
        this.cartItemId = cartItemId;
        this.quantity = quantity;
        this.userId = userId;
        this.productId = productId;
    }

    public Cart(){

    }


    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

}
