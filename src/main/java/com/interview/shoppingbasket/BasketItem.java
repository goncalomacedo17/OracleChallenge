package com.interview.shoppingbasket;

import lombok.Data;

@Data
public class BasketItem {
    private String productCode;
    private String productName;
    private int quantity;
    private double productRetailPrice;

    public BasketItem(){
        this.productCode=null;
        this.productName=null;
        this.quantity=0;
    }
    public BasketItem(String productCode,String productName,int quantity){
        this.productCode=productCode;
        this.productName=productName;
        this.quantity=quantity;
    }
    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getProductRetailPrice() {
        return productRetailPrice;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductRetailPrice(double productRetailPrice) {
        this.productRetailPrice = productRetailPrice;
    }
}
