package com.hit.hit.model;

import java.util.Objects;

public class Product {

    private int id;
    private String productName;
    private int minStock;
    private int currentStock;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return productName;}
    public void setName(String productName) {this.productName = productName;}

    public int getMinStock() {return minStock;}
    public void setMinStock(int minStock) {this.minStock = minStock;}

    public int getCurrentStock() {return currentStock;}
    public void setCurrentStock(int currentStock) {this.currentStock = currentStock;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {return Objects.hash(id, productName);}


}
