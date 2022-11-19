package com.example.orderhibfx;

import com.example.orderhibfx.dao.ClientDAO;
import com.example.orderhibfx.models.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {

        String text = "Hibernate si broken";

//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hibernate.cfg.xml");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction transaction = entityManager.getTransaction();
//
//        try {
//
//            transaction.begin();
//
//            Client client = new Client();
//            client.setName("Hibernate");
//
//            entityManager.persist(client);
//
//            transaction.commit();
//        } finally {
//            if (transaction.isActive()) {
//                transaction.rollback();
//            }
//            entityManager.close();
//            entityManagerFactory.close();
//        }



        try {
            var handlerClientes = new ClientDAO();
            text = handlerClientes.get(1).getName();
        } catch (Exception e) {
            text = "Hibernate si broken";
        } finally {
            welcomeText.setText(text);
        }

    }
}