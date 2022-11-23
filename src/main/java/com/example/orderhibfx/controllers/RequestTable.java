package com.example.orderhibfx.controllers;

import com.example.orderhibfx.models.Request;

public class RequestTable {
    String id;
    String client;
    String date;
    String delivered;
    String product;

    public RequestTable(String id, String client, String date, String delivered, String product) {
        this.id = id;
        this.client = client;
        this.date = date;
        this.delivered = delivered;
        this.product = product;
    }

    public RequestTable(Request request) {
        this.id = request.getId().toString();
        this.client = request.getClient();
        this.date = request.getDate().toString();
        this.delivered = request.getDelivered() == 1 ? "Si" : "No";
        this.product = request.getProduct().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDelivered() {
        return delivered;
    }

    public void setDelivered(String delivered) {
        this.delivered = delivered;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

}
