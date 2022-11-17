package com.example.orderhibfx.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Item {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "iditem")
    private int iditem;
    @Basic
    @Column(name = "order")
    private Integer order;
    @Basic
    @Column(name = "product")
    private Integer product;
    @Basic
    @Column(name = "amount")
    private Integer amount;

    public int getIditem() {
        return iditem;
    }

    public void setIditem(int iditem) {
        this.iditem = iditem;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return iditem == item.iditem && Objects.equals(order, item.order) && Objects.equals(product, item.product) && Objects.equals(amount, item.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iditem, order, product, amount);
    }
}
