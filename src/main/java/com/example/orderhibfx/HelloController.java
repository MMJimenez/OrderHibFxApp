package com.example.orderhibfx;

import com.example.orderhibfx.dao.RequestDAO;
import com.example.orderhibfx.models.Request;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {

        String text = "Hibernate si broken";



        try {
            var handlerClientes = new RequestDAO();
            Request request = new Request();
            request = handlerClientes.get(6);
            text = request.getClient() + " " + request.getDate() + " " + request.getDelivered() + " " + request.getProduct();
        } catch (Exception e) {
            text = "Hibernate si broken";
        } finally {
            welcomeText.setText(text);
        }

    }
}