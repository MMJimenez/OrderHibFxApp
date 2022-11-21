package com.example.orderhibfx.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public int id;
    @Basic
    @Column(name = "name")
    public String name;
    @Basic
    @Column(name = "type")
    public String type;
    @Basic
    @Column(name = "price")
    public Double price;
    @Basic
    @Column(name = "availibity")
    public Byte availibity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Byte getAvailibity() {
        return availibity;
    }

    public void setAvailibity(Byte availibity) {
        this.availibity = availibity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Objects.equals(name, product.name) && Objects.equals(type, product.type) && Objects.equals(price, product.price) && Objects.equals(availibity, product.availibity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, price, availibity);
    }
}
