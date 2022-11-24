package com.example.orderhibfx.models;

import org.hibernate.hql.internal.ast.tree.BooleanLiteralNode;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Request {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public Integer id;
    @Basic
    @Column(name = "date")
    public Date date;
    @Basic
    @Column(name = "client")
    public String client;
    @Basic
    @Column(name = "delivered")
    public Boolean delivered;
    @Basic
    @Column(name = "product")
    public Integer product;
    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "id", insertable = false, updatable = false)
    public Product productByProduct;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return id == request.id && Objects.equals(date, request.date) && Objects.equals(client, request.client) && Objects.equals(delivered, request.delivered) && Objects.equals(product, request.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, client, delivered, product);
    }

    public Product getProductByProduct() {
        return productByProduct;
    }

    public void setProductByProduct(Product productByProduct) {
        this.productByProduct = productByProduct;
    }

}
