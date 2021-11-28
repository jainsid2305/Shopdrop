package com.example.shopdrop.models;

public class Product {
    private int productId;
    private String name;
    private String size;
    private String brandName;
    private int mrp;
    private int sp;
    private int quantityAvailable;


    public Product(int productId, String name, String brandName, String size, int mrp, int sp, int quantityAvailable) {
        this.productId = productId;
        this.name = name;
        this.brandName = brandName;
        this.size =size;
        this.mrp = mrp;
        this.sp = sp;
        this.quantityAvailable = quantityAvailable;
    }


    public Product() {
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getBrandName() {
        return brandName;
    }

    public int getMrp() {
        return mrp;
    }

    public int getSp() {
        return sp;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setMrp(int mrp) {
        this.mrp = mrp;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }
}

