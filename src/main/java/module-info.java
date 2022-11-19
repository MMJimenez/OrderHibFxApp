module com.example.orderhibfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires lombok;
    requires org.hibernate.orm.core;
    requires org.hibernate.commons.annotations;
    requires java.naming;
    requires java.sql;
    requires java.base;

    requires mysql.connector.java;


    opens com.example.orderhibfx to javafx.fxml;
    exports com.example.orderhibfx;
    opens com.example.orderhibfx.utils to javafx.fxml;
    exports com.example.orderhibfx.utils;
    opens com.example.orderhibfx.models to javafx.fxml;
    exports com.example.orderhibfx.models;
    opens com.example.orderhibfx.dao to javafx.fxml;
    exports com.example.orderhibfx.dao;
}