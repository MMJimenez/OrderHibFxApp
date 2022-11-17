package com.example.orderhibfx.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idorder")
    private int idorder;
    @Basic
    @Column(name = "client")
    private Integer client;
    @Basic
    @Column(name = "date")
    private Timestamp date;
    @OneToMany(mappedBy = "orderByOrder")
    private Collection<com.example.orderhibfx.models.Item> itemsByIdorder;
    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "idclient")
    private com.example.orderhibfx.models.Client clientByClient;

    public int getIdorder() {
        return idorder;
    }

    public void setIdorder(int idorder) {
        this.idorder = idorder;
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return idorder == order.idorder && Objects.equals(client, order.client) && Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idorder, client, date);
    }

    public Collection<com.example.orderhibfx.models.Item> getItemsByIdorder() {
        return itemsByIdorder;
    }

    public void setItemsByIdorder(Collection<com.example.orderhibfx.models.Item> itemsByIdorder) {
        this.itemsByIdorder = itemsByIdorder;
    }

    public com.example.orderhibfx.models.Client getClientByClient() {
        return clientByClient;
    }

    public void setClientByClient(com.example.orderhibfx.models.Client clientByClient) {
        this.clientByClient = clientByClient;
    }
}
