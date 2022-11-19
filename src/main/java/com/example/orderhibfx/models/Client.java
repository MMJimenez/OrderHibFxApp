package com.example.orderhibfx.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Client implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idclient")
    private int idclient;
    @Basic
    @Column(name = "name")
    private String name;

    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return idclient == client.idclient && Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idclient, name);
    }
}
