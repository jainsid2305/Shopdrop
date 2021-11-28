package com.example.shopdrop.models;

public class OrderItem {
    private int orderItemId;
    private int quantity;
    private int productId;
    private int orderId;

    public OrderItem(int orderItemId, int quantity, int productId, int orderId) {
        this.orderItemId = orderItemId;
        this.quantity = quantity;
        this.productId = productId;
        this.orderId = orderId;
    }

    public OrderItem(){

    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
