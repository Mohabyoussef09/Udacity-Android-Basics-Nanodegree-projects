package com.example.android.inventoryapp;

/**
 * Created by mohab on 8/29/2017.
 */

public class Inventory {
    private String prod_name;
    private int quantity;
    private int price ;
    private String image ;

    public Inventory(String prod_name, int quantity,int price,String image) {
        this.prod_name = prod_name;
        this.quantity = quantity;
        this.price=price;
        this.image=image;
    }

    public String getProdName() {
        return prod_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}


