package com.example.orderhibfx.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idproduct")
    private int idproduct;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "price")
    private Double price;
    @Basic
    @Column(name = "stock")
    private Integer stock;
    @OneToMany(mappedBy = "productByProduct")
    private Collection<com.example.orderhibfx.models.Item> itemsByIdproduct;

    public int getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return idproduct == product.idproduct && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(stock, product.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idproduct, name, price, stock);
    }

    public Collection<com.example.orderhibfx.models.Item> getItemsByIdproduct() {
        return itemsByIdproduct;
    }

    public void setItemsByIdproduct(Collection<com.example.orderhibfx.models.Item> itemsByIdproduct) {
        this.itemsByIdproduct = itemsByIdproduct;
    }
}
