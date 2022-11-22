package com.example.orderhibfx;

import com.example.orderhibfx.dao.RequestDAO;
import com.example.orderhibfx.models.Request;
import com.example.orderhibfx.utils.BetaFuntions;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
/*
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
*/
        String text="";
        var a = new ArrayList<Request>();
        a = BetaFuntions.allRequestLastYear();

        for(int i = 0; i < a.size(); i++){
            text = text + a.get(i).getClient();
            text = text + "   ";
        }

        welcomeText.setText(text);



    }
}